package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CityDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String name,

    @NotNull(message = "O campo idEstado deve ser informado.")
    Long idState
) { }