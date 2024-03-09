package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Phone;
import br.unitins.topicos2.model.User;

public record PhoneResponseDTO (
    Long id,
    String areaCode,
    String number,
    String user
){
    public static PhoneResponseDTO valueOf (Phone phone) {
        return new PhoneResponseDTO(phone.getId(), phone.getAreaCode(), phone.getNumber(), phone.getUser().getEmail());
    }
}
