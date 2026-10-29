package org.example.provap1fullstack.dto;

import java.math.BigDecimal;

public class ProdutoResponse {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Long categoriaId;

    public ProdutoResponse() {}

    public ProdutoResponse(Long id, String nome, BigDecimal valor, Long categoriaId) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoriaId = categoriaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
