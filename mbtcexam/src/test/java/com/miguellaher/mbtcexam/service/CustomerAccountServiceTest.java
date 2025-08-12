package com.miguellaher.mbtcexam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.miguellaher.mbtcexam.entity.Account;
import com.miguellaher.mbtcexam.entity.AccountType;
import com.miguellaher.mbtcexam.entity.Customer;
import com.miguellaher.mbtcexam.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerAccountServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    
    @InjectMocks
    private CustomerAccountService customerAccountService;

    @Test
    public void createCustomerAccount() {
        Customer customer = new Customer();
        customer.setCustomerName("John");
        customer.setCustomerMobile("09201234567");
        customer.setCustomerEmail("John123@hmail.com");
        customer.setAddress1("123 Doe St");
        customer.setAddress2("Metro City");

        Account account = new Account(AccountType.S, 500L);
        customer.addAccount(account);

        Customer savedCustomer = customer;
        savedCustomer.setCustomerNumber(1L);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);

        Customer result = customerAccountService.createCustomerAccount(customer);

        assertNotNull(result.getCustomerNumber());
        assertEquals(customer.getCustomerName(), result.getCustomerName());
        assertEquals(customer.getCustomerMobile(), result.getCustomerMobile());
        assertEquals(customer.getCustomerEmail(), result.getCustomerEmail());
        assertEquals(customer.getAddress1(), result.getAddress1());
        assertEquals(customer.getAddress2(), result.getAddress2());
        assertEquals(customer.getAccounts().get(0), result.getAccounts().get(0));
    }

    @Test
    public void searchCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName("John");
        customer.setCustomerMobile("09201234567");
        customer.setCustomerEmail("John123@hmail.com");
        customer.setAddress1("123 Doe St");
        customer.setAddress2("Metro City");

        Account account = new Account(AccountType.S, 500L);
        customer.addAccount(account);

        Long customerNumber = 1L;
        customer.setCustomerNumber(customerNumber);

        when(customerRepository.findById(customerNumber)).thenReturn(Optional.of(customer));

        Customer result = customerAccountService.searchCustomer(customerNumber);

        assertEquals(customer.getCustomerNumber(), result.getCustomerNumber());
        assertEquals(customer.getCustomerName(), result.getCustomerName());
        assertEquals(customer.getCustomerMobile(), result.getCustomerMobile());
        assertEquals(customer.getCustomerEmail(), result.getCustomerEmail());
        assertEquals(customer.getAddress1(), result.getAddress1());
        assertEquals(customer.getAddress2(), result.getAddress2());
        assertEquals(customer.getAccounts().get(0), result.getAccounts().get(0));
    }
    
    
}
