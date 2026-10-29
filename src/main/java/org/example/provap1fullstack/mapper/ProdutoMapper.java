package org.example.provap1fullstack.mapper;

import org.example.provap1fullstack.dto.ProdutoCreateRequest;
import org.example.provap1fullstack.dto.ProdutoResponse;
import org.example.provap1fullstack.model.Produto;

public final class ProdutoMapper {

    private ProdutoMapper() {}

    public static Produto toEntity(ProdutoCreateRequest request, Long id) {
        return new Produto(id, request.getNome(), request.getValor(), request.getCategoriaId());
    }

    public static ProdutoResponse toResponse(Produto entity) {
        return new ProdutoResponse(entity.getId(), entity.getNome(), entity.getValor(), entity.getCategoriaId());
    }
}