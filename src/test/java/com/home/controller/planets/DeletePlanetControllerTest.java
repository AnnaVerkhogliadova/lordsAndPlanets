package com.home.controller.planets;

import com.home.model.Planet;
import com.home.service.PlanetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeletePlanetControllerTest {
    private Planet planet;
    private final Random rand  = new Random();
    @Autowired
    private MockMvc mvc;

    @Autowired
    PlanetService service;

    @Before
    public void initObjects() {
        planet = service.createPlanet("lll");
    }

    @Test
    public void deletePlanet() throws Exception {
        String uri = "/v1/planets/" + planet.getId();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NO_CONTENT.value(), status);
    }

    @Test
    public void deletePlanetNegative() throws Exception{
        String uri = "/v1/planets/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), status);
    }

    @Test
    public void deletePlanetNegative2() throws Exception{
        String uri = "/v1/planets/" + 46;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);
    }
}
