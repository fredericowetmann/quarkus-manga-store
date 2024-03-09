package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.ItemOrderDTO;
import br.unitins.topicos2.dto.ItemOrderResponseDTO;

public interface ItemOrderService {
    
    // recursos basicos
    List<ItemOrderResponseDTO> findAll();

    ItemOrderResponseDTO findById(Long id);

    ItemOrderResponseDTO create(Long idUser, ItemOrderDTO ItemOrderDto);

    void delete(Long idUser, Long idItemCart);

    long countItemOrder(Long id);

    public long count();

    public List<ItemOrderResponseDTO> findAllCart(Long idUser);

}
