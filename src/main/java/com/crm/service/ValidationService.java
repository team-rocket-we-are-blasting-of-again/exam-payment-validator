package com.crm.service;

import com.crm.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationService {
    private final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


    private boolean hasValidEmail(String emailAddress) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    private boolean isAllowedAmount(double amount) {
        return amount >= 10000.0;
    }

    private boolean hasOrderId(int orderId) {
        return orderId > 0;
    }

    public boolean validateTransaction(Transaction transaction) {
        return isAllowedAmount(transaction.getAmount())
                && hasOrderId(transaction.getOrderId())
                && hasValidEmail(transaction.getUserEmail());

    }

}
