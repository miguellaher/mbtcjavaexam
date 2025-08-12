package com.miguellaher.mbtcexam.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerNumber) {
        super("Customer not found with customer number: " + customerNumber);
    }
    
}
