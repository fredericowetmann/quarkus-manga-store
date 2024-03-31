package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record CollectionDTO (

    @NotBlank(message = "O nome da coleção não pode ser nulo")
    String name,
    String description
){
    
}