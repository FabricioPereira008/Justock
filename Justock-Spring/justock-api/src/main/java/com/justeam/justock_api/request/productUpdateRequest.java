package com.justeam.justock_api.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public class ProductUpdateRequest {

    @Size(max = 50, message = "O campo nome deve ter no máximo {max} caracteres")
    private String nome;

    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que 0")
    private BigDecimal preco;

    @Min(value = 0, message = "A quantidade deve ser no mínimo 0")
    private Integer quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    
}
