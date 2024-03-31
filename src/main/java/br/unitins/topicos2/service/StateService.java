package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.StateDTO;
import br.unitins.topicos2.dto.StateResponseDTO;
import jakarta.validation.Valid;

public interface StateService {

        // recursos basicos
        List<StateResponseDTO> getAll(int page, int pageSize);

        StateResponseDTO findById(Long id);
    
        StateResponseDTO create(@Valid StateDTO dto);
    
        StateResponseDTO update(Long id, StateDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<StateResponseDTO> findByName(String name);
    
        long count();

}