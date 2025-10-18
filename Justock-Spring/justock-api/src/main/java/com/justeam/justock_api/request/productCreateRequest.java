package com.justeam.justock_api.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductCreateRequest {

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(max = 50, message = "O campo nome deve ter no máximo {max} caracteres")
    private String nome;

    @NotNull(message = "O campo preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que 0")
    private BigDecimal preco;

    @NotNull(message = "O campo quantidade é obrigatório")
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
