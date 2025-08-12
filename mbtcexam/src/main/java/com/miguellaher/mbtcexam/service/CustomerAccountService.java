package com.miguellaher.mbtcexam.service;

import org.springframework.stereotype.Service;

import com.miguellaher.mbtcexam.entity.Customer;
import com.miguellaher.mbtcexam.exception.CustomerNotFoundException;
import com.miguellaher.mbtcexam.repository.CustomerRepository;

@Service
public class CustomerAccountService {
    
    private CustomerRepository customerAccountRepository;

    public CustomerAccountService(CustomerRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    public Customer createCustomerAccount(Customer customerAccount) {
        return customerAccountRepository.save(customerAccount);
    }

    public Customer searchCustomer(Long customerNumber) {
        return customerAccountRepository.findById(customerNumber)
                .orElseThrow(() -> new CustomerNotFoundException(customerNumber));
    }

    
}
