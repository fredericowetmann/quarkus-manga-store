package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Payment;

public record PaymentResponseDTO(
    String cardNumber,
    String cardHolderName,
    String cardExpiration,
    Double amount
) {
    public static PaymentResponseDTO valueOf(Payment payment) {
        return new PaymentResponseDTO(
            payment.getCardNumber(),
            payment.getCardHolderName(),
            payment.getCardExpiration(),
            payment.getAmount()
        );
    }
}
