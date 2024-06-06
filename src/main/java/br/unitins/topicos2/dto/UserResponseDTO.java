package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Profile;
import br.unitins.topicos2.model.User;

public record UserResponseDTO(
    Long id,
    String name,
    String email,
    String cpf,
    Profile profile,
    String imageName
) { 
    public static UserResponseDTO valueOf(User user){

        return new UserResponseDTO(
            user.getId(), 
            user.getName(),
            user.getEmail(),
            user.getCpf(),
            user.getProfile(),
            user.getImageName()
        );
    }
}
