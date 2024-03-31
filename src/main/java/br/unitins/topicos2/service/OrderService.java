package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.OrderDTO;
import br.unitins.topicos2.dto.OrderResponseDTO;

public interface OrderService {

        public OrderResponseDTO insert(OrderDTO dto, String login);
        public OrderResponseDTO findById(Long id);
        public List<OrderResponseDTO> findByAll();
        public List<OrderResponseDTO> findByAll(String login);
}
