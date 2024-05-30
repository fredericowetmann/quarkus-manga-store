package br.unitins.topicos2.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MangaDTO (
    
    @NotBlank(message = "O nome não pode ser vazio")
    String name,
    String description,
    @NotNull(message = "O preço não pode ser nulo")
    Double price,
    @NotNull(message = "A quantidade em estoque não pode estar nula")
    Integer inventory,
    Integer numPages,
    Integer volume,
    Long collection,
    Long publisher,
    Long author,
    List<Long> listGenre
){

}