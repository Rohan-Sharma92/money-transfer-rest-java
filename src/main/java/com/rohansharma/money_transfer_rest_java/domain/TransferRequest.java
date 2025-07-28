package com.rohansharma.money_transfer_rest_java.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class TransferRequest {

    private final UUID id;

    private final UUID walletId;

    private final UUID recipientId;

    private final BigDecimal amount;

    private final Instant createdAt;

    private Instant executedAt;

    private Instant rejectedAt;

    private Instant updatedAt;


    public TransferRequest(UUID id, UUID walletId, UUID recipientId, BigDecimal amount, Instant createdAt) {
        this.id = id;
        this.walletId = walletId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public UUID getRecipientId() {
        return recipientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(Instant executedAt) {
        this.executedAt = executedAt;
    }

    public Instant getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(Instant rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
