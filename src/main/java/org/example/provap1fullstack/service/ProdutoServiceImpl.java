package org.example.provap1fullstack.service;

import org.example.provap1fullstack.dto.ProdutoCreateRequest;
import org.example.provap1fullstack.dto.ProdutoResponse;
import org.example.provap1fullstack.exception.CategoriaInexistenteException;
import org.example.provap1fullstack.exception.ResourceNotFoundException;
import org.example.provap1fullstack.mapper.ProdutoMapper;
import org.example.provap1fullstack.model.Produto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final List<Produto> produtosMemoria;
    private final CategoriaService categoriaService;
    private final AtomicLong sequence = new AtomicLong(3);

    public ProdutoServiceImpl(List<Produto> produtosMemoria, CategoriaService categoriaService) {
        this.produtosMemoria = produtosMemoria;
        this.categoriaService = categoriaService;
    }

    @Bean
    public static List<Produto> produtosMemoriaSeed() {
        List<Produto> list = new ArrayList<>();
        list.add(new Produto(1L, "Teclado Mecânico", new BigDecimal("250.00"), 1L));
        list.add(new Produto(2L, "Effective Java", new BigDecimal("190.00"), 2L));
        list.add(new Produto(3L, "Controle sem fio", new BigDecimal("320.00"), 3L));
        return list;
    }

    @Override
    public ProdutoResponse criar(ProdutoCreateRequest request) {
        if (!categoriaService.existePorId(request.getCategoriaId())) {
            throw new CategoriaInexistenteException("categoria inexistente");
        }
        Long novoId = sequence.incrementAndGet();
        Produto produto = ProdutoMapper.toEntity(request, novoId);
        produtosMemoria.add(produto);
        return ProdutoMapper.toResponse(produto);
    }

    @Override
    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtosMemoria.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
        return ProdutoMapper.toResponse(produto);
    }

    @Override
    public List<ProdutoResponse> listarComFiltros(String nome, BigDecimal valorMinimo) {
        return produtosMemoria.stream()
                .filter(p -> nome == null || p.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(p -> valorMinimo == null || p.getValor().compareTo(valorMinimo) >= 0)
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @Override
    public ProdutoResponse atualizar(Long id, ProdutoCreateRequest request) {
        Produto produto = produtosMemoria.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));

        if (!categoriaService.existePorId(request.getCategoriaId())) {
            throw new CategoriaInexistenteException("categoria inexistente");
        }

        produto.setNome(request.getNome());
        produto.setValor(request.getValor());
        produto.setCategoriaId(request.getCategoriaId());

        return ProdutoMapper.toResponse(produto);
    }

    @Override
    public void deletar(Long id) {
        boolean removido = produtosMemoria.removeIf(p -> p.getId().equals(id));
        if (!removido) {
            throw new ResourceNotFoundException("Produto não encontrado.");
        }
    }
}
