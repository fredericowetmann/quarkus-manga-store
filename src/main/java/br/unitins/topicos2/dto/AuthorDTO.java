package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorDTO (

    @NotBlank(message = "O nome do autor não pode ser nulo")
    String name,
    String email
){
    
}
