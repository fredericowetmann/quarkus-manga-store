package br.unitins.topicos2.dto;

import java.util.List;

import br.unitins.topicos2.model.Author;
import br.unitins.topicos2.model.Collection;
import br.unitins.topicos2.model.Manga;
import br.unitins.topicos2.model.Publisher;
import br.unitins.topicos2.model.Genre;

public record MangaResponseDTO (
    Long id,
    String name,
    String description,
    Double price,
    Integer inventory,
    Integer numPages,
    Integer volume,
    Collection collection,
    Publisher publisher,
    Author author,
//    String imageName
    List<Genre> listGenre
){
    public static MangaResponseDTO valueOf(Manga manga){
        return new MangaResponseDTO(manga.getId(), manga.getName(), manga.getDescription(), manga.getPrice(), manga.getInventory(), manga.getNumPages(), manga.getVolume(), manga.getCollection(), manga.getPublisher(), manga.getAuthor(), manga.getListGenre());
    }
    
}
