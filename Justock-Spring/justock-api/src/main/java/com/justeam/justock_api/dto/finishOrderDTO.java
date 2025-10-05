package com.justeam.justock_api.dto;

import java.sql.Date;

import com.justeam.justock_api.model.order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class finishOrderDTO {
    private long id;
    private order order;
    private Date dataEntrega;
    private Date dataEmissao;
    private String statusPagamento;
    private String statusPedido;
}
