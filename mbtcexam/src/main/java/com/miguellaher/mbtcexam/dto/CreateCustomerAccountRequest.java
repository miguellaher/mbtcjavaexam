package com.miguellaher.mbtcexam.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.miguellaher.mbtcexam.entity.AccountType;

public class CreateCustomerAccountRequest {

    @NotBlank(message = "{com.miguellaher.mbtcexam.dto.CreateAccountRequest.customerName.blank}")
    private String customerName;

    @NotBlank(message = "{com.miguellaher.mbtcexam.dto.CreateAccountRequest.customerMobile.blank}")
    private String customerMobile;

    @Email(regexp = "^[\\w.+\\-]+@([\\w\\-]+\\.)+[A-Za-z]{2,}$", 
        message = "{com.miguellaher.mbtcexam.dto.CreateAccountRequest.customerEmail.invalid}")
    @NotBlank(message = "{com.miguellaher.mbtcexam.dto.CreateAccountRequest.customerEmail.blank}")
    private String customerEmail;

    @NotBlank(message = "{com.miguellaher.mbtcexam.dto.CreateAccountRequest.address1.blank}")
    private String address1;

    private String address2;

    @NotNull(message = "{com.miguellaher.mbtcexam.dto.CreateAccountRequest.accountType.blank}")
    private AccountType accountType;

    private Long availableBalance;


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
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public Long getAvailableBalance() {
        return availableBalance;
    }
    public void setAvailableBalance(Long availableBalance) {
        this.availableBalance = availableBalance;
    }
    
    
}
