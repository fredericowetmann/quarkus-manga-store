package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.PhysicalPersonDTO;
import br.unitins.topicos2.dto.PhysicalPersonResponseDTO;
import br.unitins.topicos2.model.PhysicalPerson;

public interface PhysicalPersonService {

    // recursos basicos
    List<PhysicalPersonResponseDTO> findAll();

    PhysicalPersonResponseDTO findById(Long id);

    PhysicalPersonResponseDTO create(PhysicalPersonDTO productDto);

    PhysicalPersonResponseDTO update(Long id, PhysicalPersonDTO productDto);

    void delete(Long id);

    // recursos extras

    List<PhysicalPersonResponseDTO> findByName(String name);

    long count();

    PhysicalPerson createPhysicalPerson(PhysicalPersonDTO physicalPersonDto);

}
