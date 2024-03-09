package br.unitins.topicos2.dto;

public record UpdatePasswordDTO(
    String currentPassword,
    String newPassword
) {
}
