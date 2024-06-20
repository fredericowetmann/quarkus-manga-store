package br.unitins.topicos2.dto;

public record PaymentDTO (
    String cardNumber,
    String cardHolderName,
    String cardExpiration,
    String cardCvv,
    Double amount
) {}
