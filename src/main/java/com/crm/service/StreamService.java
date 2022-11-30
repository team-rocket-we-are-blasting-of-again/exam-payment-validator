package com.crm.service;

import com.crm.bindings.StreamBindings;
import com.crm.model.InvalidTransaction;
import com.crm.model.Transaction;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;


@EnableBinding(StreamBindings.class)
@Service
public class StreamService {

    @Autowired
    private ValidationService validationService;

    @StreamListener
    public void process(@Input("new-transaction-input-channel") KStream<Transaction,Transaction> event) {
        event.foreach((k,v)-> System.out.println(k + " v.id: " + v.getOrderId()));
    }

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
