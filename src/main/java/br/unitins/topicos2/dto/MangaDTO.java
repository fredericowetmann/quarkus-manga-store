package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MangaDTO (
    
    @NotBlank(message = "O nome não pode ser nulo")
    String name,
    String description,
    @NotNull(message = "O preço não pode ser nulo")
    Double price,
    @NotNull(message = "A quantidade em estoque não pode estar nula")
    Integer inventory,
    Integer numPages,
    Integer volume,
    Long publisher,
    Long author
){

}