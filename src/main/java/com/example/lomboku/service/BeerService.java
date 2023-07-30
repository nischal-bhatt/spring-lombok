package com.example.lomboku.service;

import com.example.lomboku.model.Beer;

import java.util.UUID;

public interface BeerService {
    public Beer getBeerById(UUID id);
}
