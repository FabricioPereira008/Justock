package com.justeam.justock_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class productResponseDTO {
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
