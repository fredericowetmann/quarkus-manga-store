package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.City;

public record CityResponseDTO (
    Long id,
    String name,
    String state
){
    public static CityResponseDTO valueOf(City city){
        return new CityResponseDTO(city.getId(), city.getName(), city.getState().getname());
    }
    
}
