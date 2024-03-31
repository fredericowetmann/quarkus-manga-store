package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CollectionDTO;
import br.unitins.topicos2.dto.CollectionResponseDTO;

public interface CollectionService {
    public CollectionResponseDTO insert(CollectionDTO dto);

    public CollectionResponseDTO update(Long id, CollectionDTO dto);

    public void delete(long id);

    public CollectionResponseDTO findById(long id);

    public List<CollectionResponseDTO> findByName(String name);

    public List<CollectionResponseDTO> findAll();
    
}
