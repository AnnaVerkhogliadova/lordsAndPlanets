package com.home.controller.lords;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.controller.LordController;
import com.home.dto.LordRequest;
import com.home.model.Lord;
import com.home.model.Planet;
import com.home.utils.JsonString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
public class CreateLordControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void createLord() throws Exception {
        String uri = "/v1/planets";
        LordRequest request = new LordRequest("anna", 22);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(JsonString.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    public void createLordNegative() throws Exception {
        String uri = "/v1/planets";
        LordRequest request = new LordRequest(null, null);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(JsonString.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    }
}


