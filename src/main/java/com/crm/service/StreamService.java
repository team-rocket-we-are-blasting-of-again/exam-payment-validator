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
import org.springframework.stereotype.Service;


@Component
public class StreamService {

    @Autowired
    private ValidationService validationService;

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void validTransactions(StreamsBuilder streamsBuilder) {
        System.out.println("Hello??");
        System.out.println("Hello??");
        System.out.println("Hello??");
        KStream<String, String> messageStream = streamsBuilder
                .stream("new-transaction", Consumed.with(STRING_SERDE, STRING_SERDE));

        KStream<String, String> validTransactions = messageStream
                .filter((s, t) -> validateTransaction(new Transaction()));
        validTransactions.to("valid-transaction");


//        KTable<String, Long> wordCounts = messageStream
//                .mapValues((ValueMapper<String, String>) String::toLowerCase)
//                .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
//                .groupBy((key, word) -> word, Grouped.with(STRING_SERDE, STRING_SERDE))
//                .count();

        //  wordCounts.toStream().to("output-topic");
    }

    private boolean validateTransaction(Transaction transaction) {

        return validationService.validateTransaction(transaction);
    }

//    @StreamListener
//    public void process(@Input("new-transaction-input-channel") KStream<Transaction,Transaction> event) {
//        event.foreach((k,v)-> System.out.println(k + " v.id: " + v.getOrderId()));
//    }
//
//    @StreamListener("new-transaction")
//    @SendTo("valid-transaction")
//    public KStream<String, Transaction> validTransaction(KStream<String, Transaction> transactionKStream) {
//        return transactionKStream.filter((k, v) -> validationService.validateTransaction(v));
//    }
//
//    @StreamListener("new-transaction")
//    @SendTo("invalid-transaction")
//    public KStream<String, Transaction> invalidTransaction(KStream<String, Transaction> transactionKStream) {
//        return transactionKStream.filter((k, v) -> !validationService.validateTransaction(v));
//    }
}
