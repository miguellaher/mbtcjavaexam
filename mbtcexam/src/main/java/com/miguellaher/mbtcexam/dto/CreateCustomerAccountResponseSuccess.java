package com.miguellaher.mbtcexam.dto;

public class CreateCustomerAccountResponseSuccess extends Response {
    private Long customerNumber;

    public Long getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }


}
