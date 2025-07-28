package com.rohansharma.money_transfer_rest_java.domain.request;

import com.rohansharma.money_transfer_rest_java.domain.FxRate;
import com.rohansharma.money_transfer_rest_java.domain.Money;

import java.util.UUID;

public class MoneyTransferRequest {

    private final Money transferAmount;

    private final FxRate fxRate;

    private final UUID recipientId;

    public MoneyTransferRequest(
            Money transferAmount,
            FxRate fxRate,
            UUID recipientId
    ){

        this.transferAmount = transferAmount;
        this.fxRate = fxRate;
        this.recipientId = recipientId;
    }

    public Money getTransferAmount() {
        return transferAmount;
    }

    public FxRate getFxRate() {
        return fxRate;
    }

    public UUID getRecipientId() {
        return recipientId;
    }
}
