package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.PublisherDTO;
import br.unitins.topicos2.dto.PublisherResponseDTO;

public interface PublisherService {
    PublisherResponseDTO insert(PublisherDTO dto);

    PublisherResponseDTO update(Long id, PublisherDTO dto);

    void delete(Long id);

    PublisherResponseDTO findById(Long id);

    List<PublisherResponseDTO> findByName(String name);

    List<PublisherResponseDTO> getAll(int page, int pageSize);

    long count();
}