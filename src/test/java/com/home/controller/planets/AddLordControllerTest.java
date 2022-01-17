package com.home.controller.planets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.dto.AddLordRequest;
import com.home.dto.PlanetRequest;
import com.home.dto.PlanetResponse;
import com.home.model.Lord;
import com.home.model.Planet;
import com.home.service.LordService;
import com.home.service.PlanetService;
import com.home.utils.JsonString;
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
public class AddLordControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    PlanetService serviceP;

    @Autowired
    LordService serviceL;

    private Planet planet;
    private Lord lord;

    @Before
    public void initObjects() {
        planet = serviceP.createPlanet("anan");
        lord = serviceL.createLord("lord", 56);
    }

    @Test
    public void addLord() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        AddLordRequest request = new AddLordRequest(lord.getId());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch("/v1/planets/{id}", planet.getId())
                .content(JsonString.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
         PlanetResponse response =
                mapper.readValue(mvcResult.getResponse().getContentAsString(), PlanetResponse.class);
        assertEquals(response.getLordId(), request.getLordId());
        assertEquals(response.getName(), planet.getName());
        assertEquals(response.getId(), planet.getId());
    }
}
