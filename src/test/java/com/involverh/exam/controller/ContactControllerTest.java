package com.involverh.exam.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ContactControllerTest.class);
    private static final String API_ROOT_PAT = "/contact";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getContactControllerTest() throws Exception {
        mockMvc.perform(get(API_ROOT_PAT + "/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$").value(hasSize(3)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getContactControllerByNameTest() throws Exception {
        mockMvc.perform(get(API_ROOT_PAT + "/name/Su")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$").value(hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].name").value("Suman"))
                .andExpect(jsonPath("$[0].phoneNumber.length()").value(1))
                .andExpect(jsonPath("$[0].email").value("suman@email.com"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getContactControllerByPhoneTest() throws Exception {
        mockMvc.perform(get(API_ROOT_PAT + "/phone/5528938472")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$").value(hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Liton"))
                .andExpect(jsonPath("$[0].phoneNumber.length()").value(1))
                .andExpect(jsonPath("$[0].email").value("liton@email.com"))
                .andReturn().getResponse().getContentAsString();
    }
}
