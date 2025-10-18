package com.justeam.justock_api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
}
