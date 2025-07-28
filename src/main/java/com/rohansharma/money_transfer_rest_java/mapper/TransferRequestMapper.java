package com.rohansharma.money_transfer_rest_java.mapper;

import com.rohansharma.money_transfer_rest_java.domain.TransferRequest;
import com.rohansharma.money_transfer_rest_java.domain.request.MoneyTransferRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class TransferRequestMapper {


    public TransferRequest convert(MoneyTransferRequest request, UUID walletId){
        return new TransferRequest(
                UUID.randomUUID(),
                walletId,
                request.getRecipientId(),
                request.getTransferAmount().getAmount(),
                Instant.now()
                );
    }
}
