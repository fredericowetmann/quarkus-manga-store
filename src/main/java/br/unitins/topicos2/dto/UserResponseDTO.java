package br.unitins.topicos2.dto;

import java.util.List;

import br.unitins.topicos2.model.Profile;
import br.unitins.topicos2.model.User;

public record UserResponseDTO(
    Long id,
    String name,
    String email,
    String cpf,
    Profile profile,
    String imageName,
    List<PhoneDTO> listaPhone
) { 
    public static UserResponseDTO valueOf(User user){

        return new UserResponseDTO(
            user.getId(), 
            user.getName(),
            user.getEmail(),
            user.getCpf(),
            user.getProfile(),
            user.getImageName(),
            user.getListaPhone()
                .stream()
                .map(t -> PhoneDTO.valueOf(t)).toList()
        );
    }
}
