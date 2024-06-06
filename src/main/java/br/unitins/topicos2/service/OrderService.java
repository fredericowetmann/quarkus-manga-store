package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.OrderDTO;
import br.unitins.topicos2.dto.OrderResponseDTO;

public interface OrderService {

        OrderResponseDTO insert(OrderDTO dto, String login);
        OrderResponseDTO findByOrderId(Long id);
        List<OrderResponseDTO> findByUserId(Long id);
        List<OrderResponseDTO> findByLogin(String login);
        List<OrderResponseDTO> findAll();
}
