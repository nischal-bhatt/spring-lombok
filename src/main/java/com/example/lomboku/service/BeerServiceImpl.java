package com.example.lomboku.service;

import com.example.lomboku.controller.KukuException;
import com.example.lomboku.controller.NotFoundException;
import com.example.lomboku.model.Beer;
import com.example.lomboku.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service("beerboy")
@Slf4j
public class BeerServiceImpl implements BeerService {

    Map<UUID,Beer> beerMap;

    public BeerServiceImpl() {

        beerMap = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        beerMap.put(uuid,
                Beer.builder().id(uuid).beerStyle(BeerStyle.PILSNER).beerName("test1").build());

        uuid = UUID.randomUUID();
        beerMap.put(uuid,
                Beer.builder().id(uuid).beerStyle(BeerStyle.LAGER).beerName("test1").build());

        uuid = UUID.randomUUID();
        beerMap.put(uuid,
                Beer.builder().id(uuid).beerStyle(BeerStyle.PORTER).beerName("test1").build());


        beerMap.entrySet()
                .stream()
                .forEach(r -> System.out.println(r.getKey()));


    }

    @Override
    public Optional<Beer> getBeerById(UUID id) {

        System.out.println(id+"test");

        log.info("get beer service is cvalled");

        System.out.println(beerMap.get(id));
        Beer b = beerMap.get(id);
        if (b == null) {
            System.out.println("retirmo");
            return Optional.empty();
        }

        System.out.println("notntont");
        return Optional.of(beerMap.get(id));
    }

    @Override
    public List<Beer> getAllBeers() {
        return new ArrayList<>(beerMap.values());
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

    @Override
    public void deleteId(UUID id) {
        this.beerMap.remove(id);
    }

    @Override
    public void pathById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())) {
            existing.setBeerName(beer.getBeerName());
        }


    }
}
