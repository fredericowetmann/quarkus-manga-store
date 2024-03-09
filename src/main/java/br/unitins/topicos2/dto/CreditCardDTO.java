package br.unitins.topicos2.dto;

import java.time.LocalDate;

public record CreditCardDTO(
    String cardNumber,
    String impressedCardName,
    LocalDate dateMaturity,
    String securityCode,
    Integer cardBrand
) {
    
}
