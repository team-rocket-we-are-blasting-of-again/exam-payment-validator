package com.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction {
    private String transactionId;
    private String userEmail;
    private double amount;
    private int orderId;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", amount=" + amount +
                ", orderId=" + orderId +
                '}';
    }
}
