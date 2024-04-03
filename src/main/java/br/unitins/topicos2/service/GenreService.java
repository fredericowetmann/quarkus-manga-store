package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.GenreDTO;
import br.unitins.topicos2.dto.GenreResponseDTO;

public interface GenreService {
    GenreResponseDTO insert(GenreDTO dto);

    GenreResponseDTO update(Long id, GenreDTO dto);

    void delete(long id);

    GenreResponseDTO findById(long id);

    List<GenreResponseDTO> findByName(String name);

    List<GenreResponseDTO> getAll(int page, int pageSize);
    
    long count();
}
