package com.example.lomboku.controller;

import com.example.lomboku.model.Beer;
import com.example.lomboku.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity insertBeer(@RequestBody Beer beer)
    {
        beerService.insertBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","api/v1/beer/"+beer.getId().toString());

        return new ResponseEntity(headers,HttpStatus.CREATED);

    }

    @PutMapping("{beerId}")
    public ResponseEntity updateById(@PathVariable ("beerId") UUID beerId, @RequestBody Beer beer) throws Exception {

        this.beerService.updateObj(beerId,beer);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}
