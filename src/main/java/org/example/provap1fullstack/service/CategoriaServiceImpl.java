package org.example.provap1fullstack.service;

import org.example.provap1fullstack.dto.CategoriaResponse;
import org.example.provap1fullstack.dto.ProdutoResponse;
import org.example.provap1fullstack.exception.ResourceNotFoundException;
import org.example.provap1fullstack.mapper.ProdutoMapper;
import org.example.provap1fullstack.model.Categoria;
import org.example.provap1fullstack.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final List<Categoria> categoriasMemoria = new ArrayList<>();
    private final List<Produto> produtosMemoria;

    public CategoriaServiceImpl(List<Produto> produtosMemoria) {
        this.produtosMemoria = produtosMemoria;
        categoriasMemoria.add(new Categoria(1L, "Eletrônicos"));
        categoriasMemoria.add(new Categoria(2L, "Livros"));
        categoriasMemoria.add(new Categoria(3L, "Games"));
    }

    @Override
    public List<CategoriaResponse> listar(String nome) {
        return categoriasMemoria.stream()
                .filter(c -> nome == null || c.getNome().toLowerCase().contains(nome.toLowerCase()))
                .map(c -> new CategoriaResponse(c.getId(), c.getNome()))
                .toList();
    }

    @Override
    public List<ProdutoResponse> listarProdutosPorCategoria(Long categoriaId) {
        if (!existePorId(categoriaId)) {
            throw new ResourceNotFoundException("Categoria não encontrada.");
        }
        return produtosMemoria.stream()
                .filter(p -> p.getCategoriaId().equals(categoriaId))
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @Override
    public boolean existePorId(Long id) {
        return categoriasMemoria.stream().anyMatch(c -> c.getId().equals(id));
    }
}
