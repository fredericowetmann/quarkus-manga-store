package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserBasicDTO(
        @NotEmpty(message = "O campo nome não pode estar vazio.")
        String name,
        @NotEmpty(message = "O campo email não pode estar vazio.")
        String email,
        @NotEmpty(message = "O campo senha não pode estar vazio.")
        String password) {

}