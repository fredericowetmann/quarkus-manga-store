package br.unitins.topicos2.dto;

import java.util.List;

import br.unitins.topicos2.model.ItemOrder;

public record ItemOrderResponseDTO(
    Integer quantity,
    Double price,
    Long idComic,
    String name
) { 
    public static ItemOrderResponseDTO valueOf(ItemOrder item){
        return new ItemOrderResponseDTO(
            item.getQuantity(), 
            item.getPrice(),
            item.getComic().getId(),
            item.getComic().getName());
    }

    public static List<ItemOrderResponseDTO> valueOf(List<ItemOrder> item) {
       return item.stream().map(i -> ItemOrderResponseDTO.valueOf(i)).toList();
    }

}