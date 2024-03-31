package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Author;

public record AuthorResponseDTO (
    Long id,
    String name
){
    public static AuthorResponseDTO valueOf(Author author){
        return new AuthorResponseDTO(author.getId(), author.getName());
    }
    
}
