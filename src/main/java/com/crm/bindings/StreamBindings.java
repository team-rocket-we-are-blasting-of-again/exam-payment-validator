package com.crm.bindings;

import com.crm.model.Transaction;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;


public interface StreamBindings {


    @Input("new-transaction-input-channel")
    KStream<String, Transaction> inputStream();
//
//    @Output("valid-transaction-output-channel")
//    KStream<String, Transaction> validTransactionStream();
//
//    @Output("invalid-transaction-output-channel")
//    KStream<String, Transaction> invalidTransactionStream();

}