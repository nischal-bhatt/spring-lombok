package com.example.lomboku.controller;

import com.example.lomboku.model.Beer;
import com.example.lomboku.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/hibro")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("{id}")
    public Beer getBeer(@PathVariable("id") UUID id)
    {
        log.info(beerService.getBeerById(UUID.randomUUID()) + "");
       return beerService.getBeerById(id);
    }

    @GetMapping
    public Collection<Beer> getBeers() {
        return beerService.getAllBeers();
    }


}
