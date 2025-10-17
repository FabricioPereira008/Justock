package com.justeam.justock_api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class orderResponseDTO {
    private long id;
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;
    private long idItemMarketplace;
    private String status;
}
