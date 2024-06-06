package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordDTO(
    @NotBlank(message = "A senha antiga deve ser informada.")
    String oldPassword,
    @NotBlank(message = "A nova senha deve ser informada.")
    String newPassword
) {
}
