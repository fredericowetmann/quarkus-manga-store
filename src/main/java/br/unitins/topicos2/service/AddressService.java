package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.AddressDTO;
import br.unitins.topicos2.dto.AddressResponseDTO;

public interface AddressService {

    public AddressResponseDTO insert(AddressDTO dto);

    public AddressResponseDTO update(Long idAddress, AddressDTO dto);

    public void delete(Long idAddress);

    public List<AddressResponseDTO> findAll();

    public AddressResponseDTO findById(long id);

    public List<AddressResponseDTO> findByCity(long id);
    
}
