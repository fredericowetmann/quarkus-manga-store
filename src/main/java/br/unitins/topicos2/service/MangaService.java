package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.MangaDTO;
import br.unitins.topicos2.dto.MangaResponseDTO;
// import br.unitins.topicos2.form.MangaImageForm;

public interface MangaService {

    MangaResponseDTO insert(MangaDTO dto);

    MangaResponseDTO update(Long id, MangaDTO dto);

    MangaResponseDTO insertImage(Long id,String name);

    void delete(Long id);

    List<MangaResponseDTO> getAll(int page, int pageSize);

    List<MangaResponseDTO> findByName(String name);

    MangaResponseDTO findById(Long id);

    List<MangaResponseDTO> findByAuthor(String authorName);

    List<MangaResponseDTO> findByPublisher(String publisherName);

    List<MangaResponseDTO> findByCollection(String collectionName);
    
    List<MangaResponseDTO> findByGenre(Long id);

    long count();
}