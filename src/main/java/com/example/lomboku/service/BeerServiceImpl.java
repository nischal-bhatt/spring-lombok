package com.example.lomboku.service;

import com.example.lomboku.model.Beer;
import com.example.lomboku.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("beerboy")
@Slf4j
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.info("get beer service is cvalled");
        return Beer.builder()
                .id(id)
                .version(1)
                .beerStyle(BeerStyle.PILSNER)
                .upc("123456")
                .build();
    }
}
