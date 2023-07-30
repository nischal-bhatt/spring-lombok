package com.example.lomboku.controller;

import com.example.lomboku.model.Customer;
import com.example.lomboku.service.BeerService;
import com.example.lomboku.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    CustomerService beerService;



    @Test
    void getCustomerById() throws Exception {
        given(beerService.getCustomer(any(UUID.class)))
                .willReturn(Customer.builder().name("tesa").build());

        mockMvc.perform(get("/customer/"+UUID.randomUUID()))
                .andExpect(status().isOk());

    }

    @Test
    void getBeerByIdNotFound() throws Exception {

        given(beerService.getCustomer(any(UUID.class)))
                .willThrow(KukuException.class);

        mockMvc.perform(get("/customer/"+UUID.randomUUID()))
                .andExpect(status().isNotFound());

    }
}