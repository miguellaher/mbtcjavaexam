package com.miguellaher.mbtcexam.dto;

import org.springframework.http.HttpStatus;

public class Response {
    private int transactionStatusCode;
    private String transactionStatusDescription;
    
    public int getTransactionStatusCode() {
        return transactionStatusCode;
    }
    public void setTransactionStatusCode(HttpStatus transactionStatusCode) {
        this.transactionStatusCode = transactionStatusCode.value();
    }
    public String getTransactionStatusDescription() {
        return transactionStatusDescription;
    }
    public void setTransactionStatusDescription(String transactionStatusDescription) {
        this.transactionStatusDescription = transactionStatusDescription;
    }


}
