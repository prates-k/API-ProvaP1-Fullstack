package org.example.provap1fullstack.service;

import org.example.provap1fullstack.dto.CategoriaResponse;
import org.example.provap1fullstack.dto.ProdutoResponse;
import java.util.List;

public interface CategoriaService {
    List<CategoriaResponse> listar(String nome);
    List<ProdutoResponse> listarProdutosPorCategoria(Long categoriaId);
    boolean existePorId(Long id);
}