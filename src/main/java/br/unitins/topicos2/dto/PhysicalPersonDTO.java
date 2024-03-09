package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record PhysicalPersonDTO(

        @NotBlank(message = "O campo nome deve ser informado.")
        String name,

        @NotBlank(message = "O campo cpf deve ser informado.")
        String cpf,

        Integer gender

) {

}
