package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StateDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O campo nome deve possuir no máximo 60 caracteres.")
    String name,

    @NotBlank(message = "O campo sigla deve ser informado.")
    @Size(min = 2, max = 2, message = "O campo sigla deve possuir 2 caracteres.")
    String acronym
) { }