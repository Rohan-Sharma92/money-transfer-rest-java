package com.rohansharma.money_transfer_rest_java.service;

import com.rohansharma.money_transfer_rest_java.domain.TransferRequest;
import com.rohansharma.money_transfer_rest_java.repository.TransferRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransferRequestService {

    private final TransferRequestRepository repository;

    @Autowired
    public TransferRequestService(TransferRequestRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TransferRequest insert(TransferRequest req){
        return repository.insert(req);
    }

    public TransferRequest findById(UUID id){
        return repository.findById(id);
    }
}
