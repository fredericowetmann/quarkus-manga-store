package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO (
    @NotBlank(message = "O campo tem que ser informado.")
    String name,
    @NotBlank(message = "O campo tem que ser informado.")
    String email,
    @NotBlank(message = "O campo tem que ser informado.")
    String cpf
) {
}