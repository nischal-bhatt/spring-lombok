package com.example.lomboku.service;

import com.example.lomboku.controller.KukuException;
import com.example.lomboku.controller.NotFoundException;
import com.example.lomboku.model.Beer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BeerServiceImplTest {

    BeerServiceImpl impl;

    @BeforeEach
    public void setUp()
    {
        impl = new BeerServiceImpl();
    }

    @Test
    void getBeerById() {

        Beer beer = impl.getAllBeers().get(0);
        Beer beer1 = impl.getBeerById(beer.getId());
        assertEquals("test1",beer1.getBeerName());

    }

    @Test
    void getBeerByIdNotFound() {



        assertThrows(KukuException.class,()->impl.getBeerById(UUID.randomUUID()));

    }
}