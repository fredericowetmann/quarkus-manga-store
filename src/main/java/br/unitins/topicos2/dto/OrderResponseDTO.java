package br.unitins.topicos2.dto;

import java.util.Date;
import java.util.List;

import br.unitins.topicos2.model.Order;
import br.unitins.topicos2.model.PaymentStatus;

public record OrderResponseDTO(
    Long id,
    Date dataHora,
    UserResponseDTO user,
    Double totalOrder,
    List<ItemOrderResponseDTO> itens,
    PaymentResponseDTO payment,
    PaymentStatus paymentStatus,
    AddressResponseDTO address
) { 
    public static OrderResponseDTO valueOf(Order order){
        return new OrderResponseDTO(
            order.getId(), 
            order.getDataHora(),
            UserResponseDTO.valueOf(order.getUser()),
            order.getTotalOrder(),
            ItemOrderResponseDTO.valueOf(order.getItens()),
            PaymentResponseDTO.valueOf(order.getPayment()),
            order.getPaymentStatus(),
            AddressResponseDTO.valueOf(order.getAddress())
        );
    }
}
