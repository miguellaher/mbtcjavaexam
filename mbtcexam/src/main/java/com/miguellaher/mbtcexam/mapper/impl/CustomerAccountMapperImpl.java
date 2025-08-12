package com.miguellaher.mbtcexam.mapper.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.miguellaher.mbtcexam.dto.AccountDto;
import com.miguellaher.mbtcexam.dto.CreateCustomerAccountRequest;
import com.miguellaher.mbtcexam.dto.SearchCustomerResponse;
import com.miguellaher.mbtcexam.entity.Account;
import com.miguellaher.mbtcexam.entity.AccountType;
import com.miguellaher.mbtcexam.entity.Customer;
import com.miguellaher.mbtcexam.mapper.CustomerMapper;

@Service
public class CustomerAccountMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(CreateCustomerAccountRequest createAccountRequest) {
        Customer customer = new Customer();

        customer.setCustomerName(createAccountRequest.getCustomerName());
        customer.setCustomerMobile(createAccountRequest.getCustomerMobile());
        customer.setCustomerEmail(createAccountRequest.getCustomerEmail());
        customer.setAddress1(createAccountRequest.getAddress1());
        customer.setAddress2(createAccountRequest.getAddress2());
        
        // available balance hard coded for demo purposes
        customer.addAccount(new Account(
            createAccountRequest.getAccountType(), 
            Optional.ofNullable(createAccountRequest.getAvailableBalance()).orElse(0L)));

        return customer;
    }

    @Override
    public SearchCustomerResponse toSearchCustomerResponse(Customer customer) {
        SearchCustomerResponse response = new SearchCustomerResponse();
        response.setCustomerNumber(customer.getCustomerNumber());
        response.setCustomerName(customer.getCustomerName());
        response.setCustomerMobile(customer.getCustomerMobile());
        response.setCustomerEmail(customer.getCustomerEmail());
        response.setAddress1(customer.getAddress1());
        response.setAddress2(customer.getAddress2());

        List<Account> savingsAccounts = customer.getAccounts()
                                        .stream()
                                        .filter(account -> account.getAccountType() == AccountType.S)
                                        .collect(Collectors.toList());
        List<AccountDto> savingsAccountDtos = savingsAccounts.stream()
                                            .map(savingsAccount -> {
                                                return toAccountDto(savingsAccount);
                                            })
                                            .collect(Collectors.toList());
        response.setSavingsAccounts(savingsAccountDtos);

        List<Account> checkingAccounts = customer.getAccounts()
                                        .stream()
                                        .filter(account -> account.getAccountType() == AccountType.C)
                                        .collect(Collectors.toList());
        List<AccountDto> checkingAccountDtos = checkingAccounts.stream()
                                            .map(checkingAccount -> {
                                                return toAccountDto(checkingAccount);
                                            })
                                            .collect(Collectors.toList());
        response.setCheckingAccounts(checkingAccountDtos);

        return response;
    }

    @Override
    public AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType().getDescription());
        accountDto.setAvailableBalance(account.getAvailableBalance());
        
        return accountDto;
    }

}
