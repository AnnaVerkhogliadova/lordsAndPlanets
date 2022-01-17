package com.home.controller.planets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.controller.PlanetController;
import com.home.dto.PlanetResponse;
import com.home.model.Planet;
import com.home.service.PlanetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetPlanetControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    PlanetService service;

    private Planet planet;

    @Before
    public void initObjects() {
        planet = service.createPlanet("anna");
    }

    @Test
    public void getPlanet() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        String uri = "/v1/planets/" + planet.getId();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        PlanetResponse response =
                mapper.readValue(mvcResult.getResponse().getContentAsString(), PlanetResponse.class);
        assertEquals(response.getId(), planet.getId());
        assertEquals(response.getName(), planet.getName());
        assertEquals(response.getLordId(), planet.getLordId());
    }

    @Test
    public void getPlanetNegative() throws Exception {
        String uri = "/v1/planets/" + 6777;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);
    }
}
