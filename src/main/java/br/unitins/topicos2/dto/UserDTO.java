package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO (
    @NotBlank(message = "O campo tem que ser informado.")
    String name,
    @NotBlank(message = "O campo tem que ser informado.")
    String email,
    @NotBlank(message = "O campo tem que ser informado.")
    String password,
    @NotBlank(message = "O campo tem que ser informado.")
    String cpf,
    Integer idProfile,
    AddressDTO address
) {

}
