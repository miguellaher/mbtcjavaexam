package com.miguellaher.mbtcexam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Account {

    public Account() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long accountNumber;

    private AccountType accountType;

    private Long availableBalance;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_number")
    private Customer customer;

    public Account(AccountType accountType) {
        this.accountType = accountType;
    }

    public Account(AccountType accountType, Long availableBalance) {
        this.accountType = accountType;
        this.availableBalance = availableBalance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
}
