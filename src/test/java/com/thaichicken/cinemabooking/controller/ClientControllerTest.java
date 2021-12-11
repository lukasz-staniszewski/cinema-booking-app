package com.thaichicken.cinemabooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.repository.ClientRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void shouldCreateClient() throws Exception {
        ClientEntity client = new ClientEntity("Test", "GetClient", "jkow@email.com", "123456789");


        mockMvc.perform(post("/clients/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("GetClient"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jkow@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("123456789"));
    }

    @Test
    public void shouldUpdateClient() throws Exception {
        ClientEntity client = new ClientEntity("Test", "UpdateClient", "jkow@email.com", "123456789");


        MvcResult result = mockMvc.perform(post("/clients/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("UpdateClient"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jkow@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("123456789"))
                .andReturn();

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        ClientEntity client2 = new ClientEntity("Test2", "UpdateClient", "jkow2@email.com", "987654321");


        mockMvc.perform(put("/clients/client/{id}", jsonObject.get("clientId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client2)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("UpdateClient"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jkow2@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("987654321"));
    }

    @Test
    public void shouldDeleteClient() throws Exception {
        ClientEntity client = new ClientEntity("Test", "DeleteClient", "jkow@email.com", "123456789");


        MvcResult result = mockMvc.perform(post("/clients/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("DeleteClient"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jkow@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("123456789"))
                .andReturn();

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        mockMvc.perform(delete("/clients/client/{id}", jsonObject.get("clientId"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
