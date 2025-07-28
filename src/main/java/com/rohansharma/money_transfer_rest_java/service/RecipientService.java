package com.rohansharma.money_transfer_rest_java.service;

import com.rohansharma.money_transfer_rest_java.domain.recipient.Recipient;
import com.rohansharma.money_transfer_rest_java.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RecipientService {

    private final RecipientRepository repository;

    @Autowired
    public RecipientService(RecipientRepository repository){
        this.repository = repository;
    }

    @Transactional
    public Recipient insert(Recipient recipient){
        return repository.insert(recipient);
    }

    public Recipient findById(UUID id){
        return repository.findById(id);
    }
}
