package com.rohansharma.money_transfer_rest_java.repository;

import static com.example.jooq.generated.Tables.TRANSFERREQUEST;

import com.example.jooq.generated.tables.records.TransferrequestRecord;
import com.rohansharma.money_transfer_rest_java.domain.TransferRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.ZoneOffset;
import java.util.UUID;

@Repository
public class TransferRequestRepository {

    private final DSLContext dslContext;

    @Autowired
    public TransferRequestRepository(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    public TransferRequest insert(TransferRequest request){
        return dslContext.insertInto(TRANSFERREQUEST).values(new TransferrequestRecord().values(
                request.getId(),
                request.getWalletId(),
                request.getRecipientId(),
                request.getAmount(),
                request.getCreatedAt().atOffset(ZoneOffset.UTC),
                request.getUpdatedAt().atOffset(ZoneOffset.UTC),
                request.getExecutedAt().atOffset(ZoneOffset.UTC),
                request.getRejectedAt().atOffset(ZoneOffset.UTC)
        )).returning().fetchOne(record ->
                new TransferRequest(
                        record.get(TRANSFERREQUEST.ID),
                        record.get(TRANSFERREQUEST.WALLET_ID),
                        record.get(TRANSFERREQUEST.RECIPIENT_ID),
                        record.get(TRANSFERREQUEST.AMOUNT),
                        record.get(TRANSFERREQUEST.CREATED_AT).toInstant()
                ));
    }

    public TransferRequest findById(UUID id){
        return dslContext.selectFrom(TRANSFERREQUEST).where(TRANSFERREQUEST.ID.eq(id)).fetchOne(record -> {
            TransferRequest transferRequest = new TransferRequest(
                    record.get(TRANSFERREQUEST.ID),
                    record.get(TRANSFERREQUEST.WALLET_ID),
                    record.get(TRANSFERREQUEST.RECIPIENT_ID),
                    record.get(TRANSFERREQUEST.AMOUNT),
                    record.get(TRANSFERREQUEST.CREATED_AT).toInstant()
            );
            transferRequest.setUpdatedAt(record.get(TRANSFERREQUEST.UPDATED_AT).toInstant());
            transferRequest.setExecutedAt(record.get(TRANSFERREQUEST.EXECUTED_AT).toInstant());
            transferRequest.setRejectedAt(record.get(TRANSFERREQUEST.REJECTED_AT).toInstant());
            return transferRequest;
        });
    }
}
