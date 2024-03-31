package br.unitins.topicos2.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record UserDTO (
    @NotBlank(message = "O campo name não pode ser nulo.")
    String name,
    String login,
    String password,
    Integer idProfile,
    List<PhoneDTO> listaPhone
) {

}
