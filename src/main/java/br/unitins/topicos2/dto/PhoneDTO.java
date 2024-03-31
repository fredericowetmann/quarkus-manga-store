package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Phone;

public record PhoneDTO(
    String areaCode,
    String number
) {
    public static PhoneDTO valueOf(Phone phone){
        return new PhoneDTO(
            phone.getAreaCode(), 
            phone.getNumber()
        );
    }
}
