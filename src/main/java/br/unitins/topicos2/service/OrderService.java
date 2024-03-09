package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CreditCardDTO;
import br.unitins.topicos2.dto.OrderDTO;
import br.unitins.topicos2.dto.OrderResponseDTO;

public interface OrderService {

        public OrderResponseDTO insert(OrderDTO dto, String login);
        public void payUsingPix(String login);
        public void payUsingCreditCard(String login, CreditCardDTO creditCardDTO);
        public OrderResponseDTO findById(Long id);
        public List<OrderResponseDTO> findByAll();
        public List<OrderResponseDTO> findByAll(String login);
}
