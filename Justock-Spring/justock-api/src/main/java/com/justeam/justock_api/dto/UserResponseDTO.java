package com.justeam.justock_api.dto;

import lombok.Data;

@Data
public class userResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;

    public userResponseDTO(Long id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
