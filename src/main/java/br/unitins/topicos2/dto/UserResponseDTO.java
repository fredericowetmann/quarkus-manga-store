package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Profile;
import br.unitins.topicos2.model.User;

public record UserResponseDTO (
    Long id,
    String username,
    String email,
    String imageName,
    Profile profile
){
    public static UserResponseDTO valueOf(User user){
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getImageName(), user.getProfile());
    }
    
}
