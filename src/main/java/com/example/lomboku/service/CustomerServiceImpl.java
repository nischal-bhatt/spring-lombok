package com.example.lomboku.service;

import com.example.lomboku.controller.KukuException;
import com.example.lomboku.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID,Customer> customerMap = new HashMap<>();

    public CustomerServiceImpl() {

        UUID uuid = UUID.randomUUID();
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        customerMap.put(uuid,Customer.builder().id(uuid).build());
        customerMap.put(uuid1,Customer.builder().id(uuid1).build());
        customerMap.put(uuid2,Customer.builder().id(uuid2).build());


    }

    @Override
    public Customer getCustomer(UUID uuid) {

        if (customerMap.get(uuid) == null) {
            throw new KukuException("bro!");
        }

        return customerMap.get(uuid);
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(customerMap.values());
    }
}
