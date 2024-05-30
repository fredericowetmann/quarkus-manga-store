package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.User;
import jakarta.validation.constraints.NotEmpty;

public record UserBasicResponseDTO(
        @NotEmpty(message = "O campo nome não pode estar vazio.")
        String nome,
        @NotEmpty(message = "O campo email não pode estar vazio.")
        String email,
        @NotEmpty(message = "O campo senha não pode estar vazio.")
        String senha) {

    public static UserBasicResponseDTO valueOf(User user) {
        return new UserBasicResponseDTO(user.getName(), user.getEmail(), user.getPassword());
    }
}
