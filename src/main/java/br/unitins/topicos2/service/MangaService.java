package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.MangaDTO;
import br.unitins.topicos2.dto.MangaResponseDTO;
// import br.unitins.topicos2.form.MangaImageForm;

public interface MangaService {

    MangaResponseDTO insert(MangaDTO dto);

    MangaResponseDTO update(Long id, MangaDTO dto);

    MangaResponseDTO insertImage(Long id, String name);

    void delete(Long id);

    List<MangaResponseDTO> getAll(int page, int pageSize);

    // List<MangaResponseDTO> getAllRandom(int page, int pageSize);

    List<MangaResponseDTO> findByName(String name, int page, int pageSize);

    MangaResponseDTO findById(Long id);

    List<MangaResponseDTO> findByAuthor(String authorName);

    List<MangaResponseDTO> findByPublisher(String publisherName);

    List<MangaResponseDTO> findByCollection(Long id, int page, int pageSize);
    
    List<MangaResponseDTO> findByGenre(Long id, int page, int pageSize);

    long count();

    long countByName(String name);

    long countByGenre(Long genreId);

    long countByCollection(Long id);
}