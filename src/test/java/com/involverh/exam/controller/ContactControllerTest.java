package com.involverh.exam.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.involverh.exam.model.Contact;
import com.involverh.exam.model.Phone;
import com.involverh.exam.model.PhoneType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
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
                .andExpect(jsonPath("$[0].phoneNumbers.length()").value(1))
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
                .andExpect(jsonPath("$[0].phoneNumbers.length()").value(1))
                .andExpect(jsonPath("$[0].email").value("liton@email.com"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void createContactControllerTest() throws Exception {
        Contact newContact = new Contact();
        Phone phone = new Phone();
        phone.setType(PhoneType.MOBILE);
        phone.setNumber("5698675432");
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        newContact.setName("Julie");
        newContact.setEmail("jj@involverh.com");
        newContact.setPhoneNumbers(phones);
        String inputJson = mapToJson(newContact);

        mockMvc.perform(post(API_ROOT_PAT + "/")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value(newContact.getName()))
                .andExpect(jsonPath("$.phoneNumbers[0].id").value(5))
                .andExpect(jsonPath("$.phoneNumbers[0].number").value(
                        newContact.getPhoneNumbers().get(0).getNumber())
                )
                .andExpect(jsonPath("$.phoneNumbers[0].type").value(
                        newContact.getPhoneNumbers().get(0).getType().toString())
                )
                .andExpect(jsonPath("$.email").value(newContact.getEmail()))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void updateContactControllerTest() throws Exception {
        Contact newContact = new Contact();
        Phone phone = new Phone();
        phone.setId(4L);
        phone.setType(PhoneType.MOBILE);
        phone.setNumber("7793827364");
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        newContact.setId(3L);
        newContact.setName("Suman");
        newContact.setEmail("suman@email.com");
        newContact.setPhoneNumbers(phones);
        String inputJson = mapToJson(newContact);

        mockMvc.perform(put(API_ROOT_PAT + "/")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value(newContact.getName()))
                .andExpect(jsonPath("$.phoneNumbers[0].id").value(4))
                .andExpect(jsonPath("$.phoneNumbers[0].number").value(
                        newContact.getPhoneNumbers().get(0).getNumber())
                )
                .andExpect(jsonPath("$.phoneNumbers[0].type").value(
                        newContact.getPhoneNumbers().get(0).getType().toString())
                )
                .andExpect(jsonPath("$.email").value(newContact.getEmail()))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void deleteContactControllerTest() throws Exception {
        mockMvc.perform(delete(API_ROOT_PAT + "/1/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
    }
}
