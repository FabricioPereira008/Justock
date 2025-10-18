package com.justeam.justock_api.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;

public class OrderCreateRequest {

    @NotNull(message = "O campo quantidade é obrigatório")
    @Min(value = 0, message = "O valor mínimo de quantidade é 0")
    private int quantidade;

    @NotNull(message = "O campo preço unitario é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser ni mínimo 0")
    private BigDecimal precoUnitario;

    @NotNull(message = "O campo sub-total é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O subtotal deve ser no mínimo 0")
    private BigDecimal subtotal;

    @NotNull(message = "O campo id de item do marketplace é obrigatório")
    private long idItemMarketplace;

    @NotBlank(message ="O campo status do pedido é obrigatório")
    @Size(max = 50, message = "O campo status deve ter no máximo {max} caracteres")
    private String status;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public long getIdItemMarketplace() {
        return idItemMarketplace;
    }

    public void setIdItemMarketplace(long idItemMarketplace) {
        this.idItemMarketplace = idItemMarketplace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
