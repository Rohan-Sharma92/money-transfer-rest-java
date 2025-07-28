package com.rohansharma.money_transfer_rest_java.service;

import com.rohansharma.money_transfer_rest_java.domain.TransferRequest;
import com.rohansharma.money_transfer_rest_java.domain.recipient.Recipient;
import com.rohansharma.money_transfer_rest_java.domain.request.MoneyTransferRequest;
import com.rohansharma.money_transfer_rest_java.mapper.TransferRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoneyTransferService {

    private final RecipientService recipientService;

    private final TransferRequestService transferRequestService;

    private final TransferRequestMapper mapper;

    @Autowired
    public MoneyTransferService(RecipientService recipientService, TransferRequestService transferRequestService, TransferRequestMapper mapper) {
        this.recipientService = recipientService;
        this.transferRequestService = transferRequestService;
        this.mapper = mapper;
    }

    @Transactional
    public TransferRequest processTransfer(MoneyTransferRequest request){
        Recipient recipient = recipientService.findById(request.getRecipientId());
        if(recipient==null){
            throw new IllegalArgumentException("Invalid recipient");
        }
    return null;

    }
}
