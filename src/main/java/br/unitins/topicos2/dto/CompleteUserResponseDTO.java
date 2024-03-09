package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Gender;
import br.unitins.topicos2.model.PhysicalPerson;
import br.unitins.topicos2.model.Profile;
import br.unitins.topicos2.model.User;

public record CompleteUserResponseDTO(
    Long id,
    String username,
    String email,
    PhysicalPerson person,
    Profile profile,
    String imageName

) {
    public static CompleteUserResponseDTO valueOf(User user){
        return new CompleteUserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhysicalPerson(), user.getProfile(), user.getImageName());
    }
}