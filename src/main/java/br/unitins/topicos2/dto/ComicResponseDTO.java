package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Author;
import br.unitins.topicos2.model.Comic;
import br.unitins.topicos2.model.Publisher;

public record ComicResponseDTO (
    Long id,
    String name,
    Double price,
    Integer inventory,
    Integer numPages,
    Publisher publisher,
    Author author,
    String imageName
){
    public static ComicResponseDTO valueOf(Comic comic){
        return new ComicResponseDTO(comic.getId(), comic.getName(), comic.getPrice(), comic.getInventory(), comic.getNumPages(), comic.getPublisher(), comic.getAuthor(), comic.getImageName());
    }
    
}
