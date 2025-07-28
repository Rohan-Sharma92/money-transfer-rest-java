package com.rohansharma.money_transfer_rest_java.domain;

public record Iban(String value) {
    public Iban {
        if (value == null || !value.matches("[A-Z]{2}\\d{2}[A-Z0-9]{1,30}")) {
            throw new IllegalArgumentException("Invalid IBAN format: " + value);
        }
    }
}
