package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Collection;

public record CollectionResponseDTO (
    Long id,
    String name,
    String description
){
    public static CollectionResponseDTO valueOf(Collection collection){
        return new CollectionResponseDTO(collection.getId(), collection.getName(), collection.getDescription());
    }
    
}
