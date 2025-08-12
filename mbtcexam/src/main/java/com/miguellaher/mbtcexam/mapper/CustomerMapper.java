package com.miguellaher.mbtcexam.mapper;

import com.miguellaher.mbtcexam.dto.AccountDto;
import com.miguellaher.mbtcexam.dto.CreateCustomerAccountRequest;
import com.miguellaher.mbtcexam.dto.SearchCustomerResponse;
import com.miguellaher.mbtcexam.entity.Account;
import com.miguellaher.mbtcexam.entity.Customer;

public interface CustomerAccountMapper {

    Customer toCustomer(CreateCustomerAccountRequest createAccountRequest);

    SearchCustomerResponse toSearchCustomerResponse(Customer customer);

    AccountDto toAccountDto(Account account);
    
}
