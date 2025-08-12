package com.miguellaher.mbtcexam.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {

    public Customer() {

    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerNumber;

    private String customerName;

    @Column(unique = true)
    private String customerMobile;

    @Column(unique = true)
    private String customerEmail;

    private String address1;

    private String address2;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

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
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> account) {
        this.accounts = account;
    }
    public void addAccount(Account account) {
        if (this.accounts == null) {
            setAccounts(new ArrayList<>());
        }
        account.setCustomer(this);
        this.accounts.add(account);
    }

    
}
