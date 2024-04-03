package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.AddressDTO;
import br.unitins.topicos2.dto.AddressResponseDTO;

public interface AddressService {

    AddressResponseDTO insert(AddressDTO dto);

    AddressResponseDTO update(Long idAddress, AddressDTO dto);

    void delete(Long idAddress);

    List<AddressResponseDTO> findAll();

    AddressResponseDTO findById(long id);

    List<AddressResponseDTO> findByCity(long id);
    
}
