package com.miguellaher.mbtcexam.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miguellaher.mbtcexam.dto.CreateCustomerAccountRequest;
import com.miguellaher.mbtcexam.dto.Response;
import com.miguellaher.mbtcexam.dto.CreateCustomerAccountResponseSuccess;
import com.miguellaher.mbtcexam.dto.SearchCustomerResponse;
import com.miguellaher.mbtcexam.entity.Customer;
import com.miguellaher.mbtcexam.mapper.CustomerMapper;
import com.miguellaher.mbtcexam.service.CustomerAccountService;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Service
public class CustomerAccountController {

    private final CustomerMapper customerMapper;

    private final CustomerAccountService customerAccountService;

    private MessageSource messageSource;

    public CustomerAccountController(CustomerMapper customerAccountMapper,
            CustomerAccountService customerAccountService, MessageSource messageSource) {
        this.customerMapper = customerAccountMapper;
        this.customerAccountService = customerAccountService;
        this.messageSource = messageSource;
    }


    @PostMapping("/api/v1/account")
    public ResponseEntity<Response> createCustomerAccount(@Valid @RequestBody CreateCustomerAccountRequest request) {
        Customer customerAccount = customerMapper.toCustomer(request);

        Customer savedCustomerAccount = customerAccountService.createCustomerAccount(customerAccount);
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{customerNumber}")
            .buildAndExpand(savedCustomerAccount.getCustomerNumber())
            .toUri();
        CreateCustomerAccountResponseSuccess response = new CreateCustomerAccountResponseSuccess();
        response.setCustomerNumber(savedCustomerAccount.getCustomerNumber());
        response.setTransactionStatusCode(HttpStatus.CREATED);

        response.setTransactionStatusDescription(
                messageSource.getMessage("com.miguellaher.mbtcexam.dto.CreateAccountResponse.customer.created",
                null,
                LocaleContextHolder.getLocale()));
        
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/api/v1/account/{customerNumber}")
    public ResponseEntity<SearchCustomerResponse> searchCustomer(@PathVariable Long customerNumber) {
        Customer foundCustomer = customerAccountService.searchCustomer(customerNumber);

        SearchCustomerResponse response = customerMapper.toSearchCustomerResponse(foundCustomer);
        response.setTransactionStatusCode(HttpStatus.FOUND);

        response.setTransactionStatusDescription(
                messageSource.getMessage("com.miguellaher.mbtcexam.dto.SearchCustomerResponse.customer.found",
                null,
                LocaleContextHolder.getLocale()));

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }


}
