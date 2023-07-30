package com.example.lomboku.controller;

import com.example.lomboku.model.Beer;
import com.example.lomboku.service.BeerService;
import com.example.lomboku.service.BeerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(BeerController.class)
class BeerControllerIntTest {

    //@Autowired
    //BeerController beerController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    BeerService beerService;

    @Test
    void testCreateNewBeer() throws JsonProcessingException {


        Beer beer = beerServiceImpl.getAllBeers().get(0);
        beer.setUpdatedDate(LocalDateTime.now());

        System.out.println(objectMapper.writeValueAsString(beer));

    }

    BeerServiceImpl beerServiceImpl
            = new BeerServiceImpl();

    @Test
    void getBeerById() throws Exception {

        Beer test
                = beerServiceImpl.getAllBeers().get(0);

        given(beerService.getBeerById(any(UUID.class)))
                .willReturn(test);


        mockMvc.perform(get("/hibro/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.beerName",is("test1")));


        //System.out.println(beerController.getBeer(UUID.randomUUID()));
    }

    @Test
    void testGetBeers() throws Exception {
        given(beerService.getAllBeers()).willReturn(beerServiceImpl.getAllBeers());

        mockMvc.perform(get("/hibro")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));

    }
}