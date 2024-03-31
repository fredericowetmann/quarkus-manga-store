package br.unitins.topicos2.dto;

import java.util.List;

import br.unitins.topicos2.model.Profile;
import br.unitins.topicos2.model.User;

public record UserResponseDTO(
    Long id,
    String name,
    String login,
    Profile profile,
    String nameImagem,
    List<PhoneDTO> listaPhone
) { 
    public static UserResponseDTO valueOf(User user){

        return new UserResponseDTO(
            user.getId(), 
            user.getName(),
            user.getLogin(),
            user.getProfile(),
            user.getNameImagem(),
            user.getListaPhone()
                .stream()
                .map(t -> PhoneDTO.valueOf(t)).toList()
        );
    }
}
