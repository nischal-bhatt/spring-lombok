package com.example.lomboku.service;

import com.example.lomboku.model.Beer;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface BeerService {
    public Beer getBeerById(UUID id);

    Collection<Beer> getAllBeers();
    Beer insertBeer(Beer beer);

    void updateObj(UUID beerId, Beer beer) throws Exception;
}
