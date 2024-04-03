package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.AuthorDTO;
import br.unitins.topicos2.dto.AuthorResponseDTO;

public interface AuthorService {
    AuthorResponseDTO insert(AuthorDTO dto);

    AuthorResponseDTO update(Long id, AuthorDTO dto);

    void delete(long id);

    AuthorResponseDTO findById(long id);

    List<AuthorResponseDTO> findByName(String name);

    List<AuthorResponseDTO> getAll(int page, int pageSize);

    long count();
}
