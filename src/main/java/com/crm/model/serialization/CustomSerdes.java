package com.crm.model.serialization;

import com.crm.model.Transaction;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
public final class CustomSerdes {
    private CustomSerdes() {}
    public static Serde<Transaction> Transaction() {
        JsonSerializer<Transaction> serializer = new JsonSerializer<>();
        JsonDeserializer<Transaction> deserializer = new JsonDeserializer<>(Transaction.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }


}