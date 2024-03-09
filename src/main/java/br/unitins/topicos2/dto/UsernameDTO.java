package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.User;

public record UsernameDTO(
    String username
) {
    public static UsernameDTO valueOf(User user) {
        return new UsernameDTO(user.getUsername());
    }
}
