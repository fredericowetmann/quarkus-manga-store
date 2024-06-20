package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Address;
import br.unitins.topicos2.model.City;

public record AddressResponseDTO (
    String name,
    String postalCode,
    String address,
    String complement,
    City city
){
    public static AddressResponseDTO valueOf(Address address){
        return new AddressResponseDTO(
            address.getName(),
            address.getPostalCode(),
            address.getAddress(),
            address.getComplement(),
            address.getCity());
    }
    
}