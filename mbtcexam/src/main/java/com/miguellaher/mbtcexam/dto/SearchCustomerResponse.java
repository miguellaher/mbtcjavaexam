package com.miguellaher.mbtcexam.dto;

import java.util.List;

public class SearchCustomerResponse extends Response {

    private Long customerNumber;

    private String customerName;

    private String customerMobile;

    private String customerEmail;

    private String address1;

    private String address2;

    private List<AccountDto> savings;

    private List<AccountDto> checking;
    

    public Long getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerMobile() {
        return customerMobile;
    }
    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public List<AccountDto> getSavingsAccounts() {
        return savings;
    }
    public void setSavingsAccounts(List<AccountDto> savings) {
        this.savings = savings;
    }
    public List<AccountDto> getCheckingAccounts() {
        return checking;
    }
    public void setCheckingAccounts(List<AccountDto> checking) {
        this.checking = checking;
    }


}
