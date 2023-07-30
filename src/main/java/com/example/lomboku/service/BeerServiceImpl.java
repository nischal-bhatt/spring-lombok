package com.example.lomboku.service;

import com.example.lomboku.model.Beer;
import com.example.lomboku.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("beerboy")
@Slf4j
public class BeerServiceImpl implements BeerService {

    Map<UUID,Beer> beerMap = new HashMap<>();

    public BeerServiceImpl() {

        UUID uuid = UUID.randomUUID();
        beerMap.put(uuid,
                Beer.builder().id(uuid).beerStyle(BeerStyle.PILSNER).build());

        uuid = UUID.randomUUID();
        beerMap.put(uuid,
                Beer.builder().id(uuid).beerStyle(BeerStyle.LAGER).build());

        uuid = UUID.randomUUID();
        beerMap.put(uuid,
                Beer.builder().id(uuid).beerStyle(BeerStyle.PORTER).build());


        beerMap.entrySet()
                .stream()
                .forEach(r -> System.out.println(r.getKey()));


    }

    @Override
    public Beer getBeerById(UUID id) {

        log.info("get beer service is cvalled");
        return beerMap.get(id);
    }

    @Override
    public Collection<Beer> getAllBeers() {
        return beerMap.values();
    }

    @Override
    public Beer insertBeer(Beer beer) {
        this.beerMap.put(beer.getId(),beer);
        return beer;
    }

    @Override
    public void updateObj(UUID beerId, Beer beer) throws Exception {
        if (beerMap.containsKey(beerId)) {
            System.out.println("updating");
            beerMap.put(beerId,beer);
        }else{
            throw new Exception("cannot find beer la dei");
        }
    }
}
