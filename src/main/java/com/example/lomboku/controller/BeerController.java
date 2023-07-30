package com.example.lomboku.controller;

import com.example.lomboku.model.Beer;
import com.example.lomboku.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/test")
    public Beer getBeer()
    {
        log.info(beerService.getBeerById(UUID.randomUUID()).toString());
       return beerService.getBeerById(UUID.randomUUID());
    }


}
