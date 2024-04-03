package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CollectionDTO;
import br.unitins.topicos2.dto.CollectionResponseDTO;

public interface CollectionService {
    CollectionResponseDTO insert(CollectionDTO dto);

    CollectionResponseDTO update(Long id, CollectionDTO dto);

    void delete(long id);

    CollectionResponseDTO findById(long id);

    List<CollectionResponseDTO> findByName(String name);

    List<CollectionResponseDTO> getAll(int page, int pageSize);
    
    long count();
}
