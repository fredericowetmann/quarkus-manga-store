package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.User;

public record LoginResponseDTO (
    String email
) {
    public static LoginResponseDTO valueOf(User user){
        return new LoginResponseDTO(user.getEmail());
    }
}