package com.rohansharma.money_transfer_rest_java.domain.recipient;

import com.rohansharma.money_transfer_rest_java.domain.Iban;

import java.util.Currency;
import java.util.UUID;

public class Recipient {

    private final UUID id;
    private final String name;
    private final Iban iban;
    private final Currency currency;

    public Recipient(UUID id, String name, Iban iban, Currency currency) {
        this.id = id;
        this.name = name;
        this.iban = iban;
        this.currency = currency;
    }

    public UUID getId() {
        return id;
    }

    public Iban getIban() {
        return iban;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }
}
