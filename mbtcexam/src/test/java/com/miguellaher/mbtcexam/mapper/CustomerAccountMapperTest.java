package com.miguellaher.mbtcexam.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.miguellaher.mbtcexam.dto.AccountDto;
import com.miguellaher.mbtcexam.dto.CreateCustomerAccountRequest;
import com.miguellaher.mbtcexam.dto.SearchCustomerResponse;
import com.miguellaher.mbtcexam.entity.Account;
import com.miguellaher.mbtcexam.entity.AccountType;
import com.miguellaher.mbtcexam.entity.Customer;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerAccountMapperTest {

    @Autowired
    private CustomerMapper customerAccountMapper;

    @Test
    public void createCustomerAccountRequest_to_customer() throws Exception {
        CreateCustomerAccountRequest request = new CreateCustomerAccountRequest();
        request.setCustomerName("John");
        request.setCustomerMobile("09201234567");
        request.setCustomerEmail("John123@hmail.com");
        request.setAddress1("123 Doe St");
        request.setAddress2("Metro City");
        request.setAccountType(AccountType.S);
        request.setAvailableBalance(500L);

        Customer customer = customerAccountMapper.toCustomer(request);

        assertEquals(request.getCustomerName(), customer.getCustomerName());
        assertEquals(request.getCustomerMobile(), customer.getCustomerMobile());
        assertEquals(request.getCustomerEmail(), customer.getCustomerEmail());
        assertEquals(request.getAddress1(), customer.getAddress1());
        assertEquals(request.getAddress2(), customer.getAddress2());
        assertEquals(request.getAccountType(), customer.getAccounts().get(0).getAccountType());
        assertEquals(request.getAvailableBalance(), customer.getAccounts().get(0).getAvailableBalance());
    }

    @Test
    public void customer_to_searchCustomerResponse() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerName("John");
        customer.setCustomerMobile("09201234567");
        customer.setCustomerEmail("John123@hmail.com");
        customer.setAddress1("123 Doe St");
        customer.setAddress2("Metro City");

        Account account = new Account(AccountType.S, 500L);
        customer.addAccount(account);

        SearchCustomerResponse response = customerAccountMapper.toSearchCustomerResponse(customer);

        assertEquals(customer.getCustomerName(), response.getCustomerName());
        assertEquals(customer.getCustomerMobile(), response.getCustomerMobile());
        assertEquals(customer.getCustomerEmail(), response.getCustomerEmail());
        assertEquals(customer.getAddress1(), response.getAddress1());
        assertEquals(customer.getAddress2(), response.getAddress2());
        assertEquals(customer.getAccounts().get(0).getAccountType().getDescription(), 
                    response.getSavingsAccounts().get(0).getAccountType());
        assertEquals(customer.getAccounts().get(0).getAvailableBalance(), 
                    response.getSavingsAccounts().get(0).getAvailableBalance());
    }

    @Test
    public void account_to_accountDto() throws Exception {
        Account account = new Account();
        account.setAccountNumber(1L);
        account.setAccountType(AccountType.S);
        account.setAvailableBalance(500L);

        AccountDto accountDto = customerAccountMapper.toAccountDto(account);

        assertEquals(account.getAccountNumber(), accountDto.getAccountNumber());
        assertEquals(account.getAccountType().getDescription(), accountDto.getAccountType());
        assertEquals(account.getAvailableBalance(), accountDto.getAvailableBalance());
    }
    
}
