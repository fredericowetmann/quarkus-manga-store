package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CityResponseDTO;

public interface CityService {

    public CityResponseDTO findById(Long id);

    public List<CityResponseDTO> findByName(String name);

    public List<CityResponseDTO> findAll();
}
