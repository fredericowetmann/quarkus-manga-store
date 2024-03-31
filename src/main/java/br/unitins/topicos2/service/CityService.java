package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CityDTO;
import br.unitins.topicos2.dto.CityResponseDTO;

public interface CityService {

        // recursos basicos
        List<CityResponseDTO> getAll();

        CityResponseDTO findById(Long id);
    
        CityResponseDTO create(CityDTO dto);
    
        CityResponseDTO update(Long id, CityDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<CityResponseDTO> findByName(String name);
    
        long count();
    
}