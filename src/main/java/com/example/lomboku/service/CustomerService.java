package com.example.lomboku.service;

import com.example.lomboku.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getCustomer(UUID uuid);
    List<Customer> getCustomers();
}
