package br.unitins.topicos2.dto;

public record AddressDTO (
    String name,
    String postalCode,
    String address,
    String complement,
    Long city
){

    
}