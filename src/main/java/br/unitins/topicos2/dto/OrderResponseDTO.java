package br.unitins.topicos2.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos2.model.Order;

public record OrderResponseDTO(
    Long id,
    LocalDateTime dataHora,
    UserResponseDTO user,
    Double totalOrder,
    List<ItemOrderResponseDTO> itens
) { 
    public static OrderResponseDTO valueOf(Order order){
        return new OrderResponseDTO(
            order.getId(), 
            order.getDataHora(),
            UserResponseDTO.valueOf(order.getUser()),
            order.getTotalOrder(),
            ItemOrderResponseDTO.valueOf(order.getItens()));
    }
}
