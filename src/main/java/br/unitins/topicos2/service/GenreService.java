package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.GenreDTO;
import br.unitins.topicos2.dto.GenreResponseDTO;

public interface GenreService {
    public GenreResponseDTO insert(GenreDTO dto);

    public GenreResponseDTO update(Long id, GenreDTO dto);

    public void delete(Long id);

    public GenreResponseDTO findById(Long id);

    public List<GenreResponseDTO> findByName(String name);

    public List<GenreResponseDTO> findAll();
}
