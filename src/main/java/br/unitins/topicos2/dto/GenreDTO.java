package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record GenreDTO (

    @NotBlank(message = "O nome do genero não pode ser nulo")
    String name,
    String description
){
    
}