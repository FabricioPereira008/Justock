package com.justeam.justock_api.dto;

import java.sql.Date;

import com.justeam.justock_api.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FinishOrderDTO {
    private long id;
    private Order order;
    private Date dataEntrega;
    private Date dataEmissao;
    private String statusPagamento;
    private String statusPedido;
}
