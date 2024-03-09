package br.unitins.topicos2.dto;

import java.util.List;

public record OrderDTO (
    // AddressDTO address,
    List<ItemOrderDTO> itens
) {

}
