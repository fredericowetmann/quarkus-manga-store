package br.unitins.topicos2.dto;


import java.util.List;

public record OrderDTO (
    // FormaPagamento pagamento,
    // EnderecoDTO endereco,
    List<ItemOrderDTO> itens
) {

}
