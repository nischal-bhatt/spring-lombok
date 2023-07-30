package com.example.lomboku.controller;

import com.example.lomboku.model.Beer;
import com.example.lomboku.model.BeerStyle;
import com.example.lomboku.service.BeerService;
import com.example.lomboku.service.BeerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Captor
    ArgumentCaptor<UUID> uuidCaptor;

    @Captor
    ArgumentCaptor<Beer> beerCaptor;


    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void testCreateNewBeer() throws Exception {


        Beer beer = beerServiceImpl.getAllBeers().get(0);
        beer.setBeerStyle(BeerStyle.PILSNER);
        beer.setVersion(null);
        beer.setId(null);

        given(beerService.insertBeer(any(Beer.class)))
                .willReturn(beerServiceImpl.getAllBeers().get(1));


        mockMvc.perform(post("/hibro")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));




    }

    @Test
    void testUpdateNewBeer() throws Exception {


        Beer beer = beerServiceImpl.getAllBeers().get(0);
        UUID uuid = UUID.randomUUID();

        mockMvc.perform(put("/hibro/" + uuid.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                        .andExpect(status().isAccepted());

        verify(beerService,times(1)).updateObj(uuid,beer);





    }

    @Test
    void testDelete() throws Exception {
        Beer beer = beerServiceImpl.getAllBeers().get(0);

        mockMvc.perform(delete("/hibro/" + beer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


        verify(beerService,times(1)).deleteId(uuidCaptor.capture());

        assertThat(beer.getId()).isEqualTo(uuidCaptor.getValue());
    }

    @Test
    void testPatch() throws Exception {

        Beer beer = beerServiceImpl.getAllBeers().get(0);

        Map<String,Object> beerMap = new HashMap<>();

        beerMap.put("beerName","new Name");

        mockMvc.perform(patch("/hibro/" + beer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isNoContent());

        verify(beerService,times(1)).pathById(uuidCaptor.capture(),beerCaptor.capture());
        assertThat(beerCaptor.getValue().getBeerName()).isEqualTo(beerMap.get("beerName"));
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

    @Test
    void getBeerByIdNotFound() throws Exception {

        given(beerService.getBeerById(any(UUID.class)))
                .willThrow(KukuException.class);

        mockMvc.perform(get("/hibro/"+UUID.randomUUID()))
                .andExpect(status().isNotFound());

    }
}