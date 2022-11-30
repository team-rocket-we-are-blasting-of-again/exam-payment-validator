package com.crm.service;

import com.crm.model.Transaction;
import com.crm.model.serialization.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StreamService {

    @Autowired
    private ValidationService validationService;

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<Transaction> TRANSACTION_SERDE = CustomSerdes.Transaction();

    @Autowired
    void validTransactions(StreamsBuilder streamsBuilder) {
        KStream<String, Transaction> messageStream = streamsBuilder
                .stream("new-transaction", Consumed.with(STRING_SERDE, TRANSACTION_SERDE));
        KStream<String, Transaction> validTransactions = messageStream
                .filter((s, t) -> validateTransaction(t));
        validTransactions.to("valid-transaction");

    }
    @Autowired
    void invalidTransactions(StreamsBuilder streamsBuilder) {
        KStream<String, Transaction> messageStream = streamsBuilder
                .stream("new-transaction", Consumed.with(STRING_SERDE, TRANSACTION_SERDE));
        KStream<String, Transaction> validTransactions = messageStream
                .filter((s, t) -> !validateTransaction(t));
        validTransactions.to("invalid-transaction");

    }
    private boolean validateTransaction(Transaction transaction) {
        boolean isValid = validationService.validateTransaction(transaction);
        return isValid;
    }
}
