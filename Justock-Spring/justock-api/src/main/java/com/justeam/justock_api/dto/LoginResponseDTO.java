package com.justeam.justock_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class loginResponseDTO {
    private String token;
    private String email;
    private String name;
    private String role;
}
