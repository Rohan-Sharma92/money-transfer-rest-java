package com.rohansharma.money_transfer_rest_java.repository;

import com.example.jooq.generated.Tables;
import static com.example.jooq.generated.tables.Recipient.RECIPIENT;

import com.rohansharma.money_transfer_rest_java.domain.Iban;
import com.rohansharma.money_transfer_rest_java.domain.recipient.Recipient;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Currency;

@Repository
public class RecipientRepository {

    private final DSLContext dslContext;

    @Autowired
    public RecipientRepository(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    public Recipient insert(Recipient recipient){
        return dslContext.insertInto(Tables.RECIPIENT).values(recipient).returning().fetchOne(record ->
                new Recipient(
                        record.get(RECIPIENT.ID),
                        record.get(RECIPIENT.NAME),
                        new Iban(record.get(RECIPIENT.IBAN)),
                        Currency.getInstance(record.get(RECIPIENT.CURRENCY))
                ));
    }
}
