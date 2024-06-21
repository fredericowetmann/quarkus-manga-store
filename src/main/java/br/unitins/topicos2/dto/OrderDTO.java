package br.unitins.topicos2.dto;


import java.util.List;

public record OrderDTO (
    PaymentDTO payment,
    List<ItemOrderDTO> itens,
    AddressDTO address
) {

} 
