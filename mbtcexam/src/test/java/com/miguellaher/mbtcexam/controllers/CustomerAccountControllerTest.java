package com.miguellaher.mbtcexam.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguellaher.mbtcexam.dto.CreateCustomerAccountRequest;
import com.miguellaher.mbtcexam.entity.AccountType;
import com.miguellaher.mbtcexam.entity.Customer;
import com.miguellaher.mbtcexam.exception.CustomerNotFoundException;
import com.miguellaher.mbtcexam.mapper.CustomerMapper;
import com.miguellaher.mbtcexam.service.CustomerAccountService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CustomerAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerMapper customerAccountMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerAccountService customerAccountService;

    @Test
    public void createCustomer_returnCreated() throws Exception {
        CreateCustomerAccountRequest request = new CreateCustomerAccountRequest();
        request.setCustomerName("John");
        request.setCustomerMobile("09201234567");
        request.setCustomerEmail("John123@hmail.com");
        request.setAddress1("Block 5 Doe St.");
        request.setAddress2("Metro City");
        request.setAccountType(AccountType.S);
        request.setAvailableBalance(100L);

        Customer customer = customerAccountMapper.toCustomer(request);
        customer.setCustomerNumber(1L);

        when(customerAccountService.createCustomerAccount(any())).thenReturn(customer);
        

        ResultActions response = mockMvc.perform(post("/api/v1/account")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createCustomer_noCustomerName_returnBadRequest() throws Exception {
        CreateCustomerAccountRequest request = new CreateCustomerAccountRequest();
        request.setCustomerMobile("09201234567");
        request.setCustomerEmail("John123@hmail.com");
        request.setAddress1("Block 5 Doe St.");
        request.setAddress2("Metro City");
        request.setAccountType(AccountType.S);
        request.setAvailableBalance(100L);

        ResultActions response = mockMvc.perform(post("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createCustomer_invalidAccountType_returnBadRequest() throws Exception {
        String invalidAccountType = "{\r\n" + //
                        "   \"customerName\":\"testasd\",\r\n" + //
                        "   \"customerMobile\":\"09081234567\",\r\n" + //
                        "   \"customerEmail\":\"test12123@gmail.com\",\r\n" + //
                        "   \"address1\":\"testasd\",\r\n" + //
                        "   \"address2\":\"sadfsdf\",\r\n" + //
                        "   \"accountType\":\"X\"\r\n" + //
                        "}";

        ResultActions response = mockMvc.perform(post("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidAccountType));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void searchCustomer_returnFound() throws Exception {
        CreateCustomerAccountRequest request = new CreateCustomerAccountRequest();
        request.setCustomerName("John");
        request.setCustomerMobile("09201234567");
        request.setCustomerEmail("John123@hmail.com");
        request.setAddress1("Block 5 Doe St.");
        request.setAddress2("Metro City");
        request.setAccountType(AccountType.S);
        request.setAvailableBalance(100L);

        Long customerNumberSearch = 1L;

        Customer customer = customerAccountMapper.toCustomer(request);
        customer.setCustomerNumber(customerNumberSearch);
        when(customerAccountService.searchCustomer(customerNumberSearch)).thenReturn(customer);

        ResultActions response = mockMvc.perform(get("/api/v1/account/" + customerNumberSearch));

        response.andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    public void searchCustomer_returnNotFound() throws Exception {
        Long customerNumberSearch = 404L;

        when(customerAccountService.searchCustomer(customerNumberSearch)).thenThrow(CustomerNotFoundException.class);

        ResultActions response = mockMvc.perform(get("/api/v1/account/" + customerNumberSearch));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}
