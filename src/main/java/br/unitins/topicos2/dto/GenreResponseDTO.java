package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Genre;

public record GenreResponseDTO (
    Long id,
    String name
){
    public static GenreResponseDTO valueOf(Genre genre){
        return new GenreResponseDTO(genre.getId(), genre.getName());
    }
}
