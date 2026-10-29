package org.example.provap1fullstack.service;

import org.example.provap1fullstack.dto.ProdutoCreateRequest;
import org.example.provap1fullstack.dto.ProdutoResponse;
import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {
    ProdutoResponse criar(ProdutoCreateRequest request);
    ProdutoResponse buscarPorId(Long id);
    List<ProdutoResponse> listarComFiltros(String nome, BigDecimal valorMinimo);
    ProdutoResponse atualizar(Long id, ProdutoCreateRequest request);
    void deletar(Long id);
}
