package com.justeam.justock_api.request;

import com.justeam.justock_api.model.Order;

import java.sql.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FinishOrderCreateRequest {

    private long id;

    @NotNull(message = "O campo Order é obrigatório")
    private Order order;

    @NotNull(message = "A data de entrega é obrigatória")
    private Date dataEntrega;

    @NotNull(message = "A data de emissão é obrigatória")
    private Date dataEmissao;

    @NotBlank(message = "O status do pagamento é obrigatório")
    @Size(max = 50, message = "O status do pagamento deve ter no máximo {max} caracteres")
    private String statusPagamento;

    @NotBlank(message = "O status do pedido é obrigatório")
    @Size(max = 50, message = "O status do pedido deve ter no máximo {max} caracteres")
    private String statusPedido;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }
}