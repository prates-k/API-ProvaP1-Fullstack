package org.example.provap1fullstack.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ProdutoCreateRequest {

    @NotBlank(message = "não pode ser vazio")
    @Size(min = 2, message = "mínimo de 2 caracteres")
    private String nome;

    @NotNull(message = "não pode ser nulo")
    @DecimalMin(value = "0.01", message = "deve ser >= 0.01")
    private BigDecimal valor;

    @NotNull(message = "não pode ser nulo")
    private Long categoriaId;

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
