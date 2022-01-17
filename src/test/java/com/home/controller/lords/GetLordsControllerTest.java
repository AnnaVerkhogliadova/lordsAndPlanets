package com.home.controller.lords;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.dto.LordResponse;
import com.home.dto.PlanetResponse;
import com.home.model.Lord;
import com.home.service.LordService;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetLordsControllerTest {

    private Lord lord;
    private Random rand  = new Random();

    @Autowired
    private MockMvc mvc;

    @Autowired
    LordService service;

    @Before
    public void initObjects() {
        Lord lord1 = service.createLord("anna", rand.nextInt());
        Lord lord2 = service.createLord("denis", rand.nextInt());
        Lord lord3 = service.createLord("kek", rand.nextInt());
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void getLords() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        String uri = "/v1/lords";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        Lord[] lords = mapFromJson(content, Lord[].class);
        assertTrue(lords.length>0);

//        LordResponse response =
//                mapper.readValue(mvcResult.getResponse().getContentAsString(), LordResponse.class);
//        assertEquals(response.getId(), lord.getId());
//        assertEquals(response.getName(), lord.getName());
//        assertEquals(response.getAge(), lord.getAge());
    }
}
