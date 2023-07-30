package com.example.lomboku.controller;

import com.example.lomboku.model.Customer;
import com.example.lomboku.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {


    private final CustomerService customerService;


    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId)
    {
        return customerService.getCustomer(customerId);
    }

    @GetMapping
    public List<Customer> customers() {
        return customerService.getCustomers();
    }
}
