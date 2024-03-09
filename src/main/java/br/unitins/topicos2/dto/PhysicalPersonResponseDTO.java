package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.PhysicalPerson;
import br.unitins.topicos2.model.Gender;

public record PhysicalPersonResponseDTO(
        Long id,
        String name,
        String cpf,
        String gender) {
    public static PhysicalPersonResponseDTO valueOf(PhysicalPerson physicalPerson) {
        return new PhysicalPersonResponseDTO(physicalPerson.getId(), physicalPerson.getName(), physicalPerson.getCpf(), physicalPerson.getGender().getLabel());
    }
}
