package com.thaichicken.cinemabooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.ClientRole;
import com.thaichicken.cinemabooking.repository.ClientRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientRepository clientRepository;

    ClientEntity CLIENT_1 = new ClientEntity(1, "Geralt", "ofRivia", "witcher@mail.com", "777666555", "password", ClientRole.USER);
    ClientEntity CLIENT_2 = new ClientEntity(2, "Yenneffer", "ofVengerberg", "yenn@mail.com", "111222333", "password", ClientRole.USER);

    @Test
    @WithMockUser
    public void getAllClients_success() throws Exception {
        List<ClientEntity> records = new ArrayList<>(Arrays.asList(CLIENT_1, CLIENT_2));

        Mockito.when(clientRepository.findAll()).thenReturn(records);

        mockMvc.perform(get("/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Geralt"))
                .andExpect(jsonPath("$[1].name").value("Yenneffer"));
    }

    @Test
    @WithMockUser
    public void getClientById_success() throws Exception {
        Mockito.when(clientRepository.findById(CLIENT_1.getClientId())).thenReturn(Optional.of(CLIENT_1));

        mockMvc.perform(get("/clients/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name").value("Geralt"));
    }

    @Test
    @WithMockUser
    public void createClient_success() throws Exception {
        ClientEntity record = new ClientEntity("Cirilla", "Riannon", "ciri@mail.com", "999888777", "password", ClientRole.USER);

        Mockito.when(clientRepository.save(record)).thenReturn(record);
        String recordStr = "{\"clientId\":0,\"name\":\"Cirilla\",\"surname\":\"Riannon\",\"email\":\"ciri@mail.com\",\"phone\":\"999888777\",\"password\":\"password\",\"clientRole\":\"USER\"}";
        MockHttpServletRequestBuilder mockRequest = post("/clients/client")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(recordStr);


        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name").value("Cirilla"));
    }

    @Test
    @WithMockUser
    public void updateClient_success() throws Exception {
        ClientEntity record = new ClientEntity(1, "Jaskier", "Jaskier", "jaskier@mail.com", "999888777", "password", ClientRole.USER);

        Mockito.when(clientRepository.findById(CLIENT_1.getClientId())).thenReturn(Optional.of(CLIENT_1));
        Mockito.when(clientRepository.save(record)).thenReturn(record);

        String recordStr = "{\"clientId\":1,\"name\":\"Jaskier\",\"surname\":\"Jaskier\",\"email\":\"jaskier@mail.com\",\"phone\":\"999888777\",\"password\":\"password\",\"clientRole\":\"USER\"}";
        MockHttpServletRequestBuilder mockRequest = put("/clients/client/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(recordStr);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name").value("Jaskier"));
    }

    @Test
    @WithMockUser
    public void updateClient_nullId() throws Exception {
        ClientEntity record = new ClientEntity("Jaskier", "Jaskier", "jaskier@mail.com", "999888777", "password", ClientRole.USER);

        String recordStr = "{\"clientId\":0,\"name\":\"Jaskier\",\"surname\":\"Jaskier\",\"email\":\"jaskier@mail.com\",\"phone\":\"999888777\",\"password\":\"password\",\"clientRole\":\"USER\"}";

        MockHttpServletRequestBuilder mockRequest = put("/clients/client/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(recordStr);

//        System.out.println(objectMapper.writeValueAsString(record));
        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result ->
                        assertEquals("Client not found with id 1", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @WithMockUser
    public void updateClient_recordNotFound() throws Exception {
        ClientEntity record = new ClientEntity(5, "Jaskier", "Jaskier", "jaskier@mail.com", "999888777", "password", ClientRole.USER);
        String recordStr = "{\"clientId\":5,\"name\":\"Jaskier\",\"surname\":\"Jaskier\",\"email\":\"jaskier@mail.com\",\"phone\":\"999888777\",\"password\":\"password\",\"clientRole\":\"USER\"}";
        MockHttpServletRequestBuilder mockRequest = put("/clients/client/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(recordStr);

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    @WithMockUser
    public void deleteClient_success() throws Exception {
        Mockito.when(clientRepository.findById(CLIENT_2.getClientId())).thenReturn(Optional.of(CLIENT_2));

        mockMvc.perform(delete("/clients/client/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void deleteClient_idNotFound() throws Exception {
        // Mockito.when(clientRepository.findById(5)).thenReturn(null);

        mockMvc.perform(delete("/clients/client/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result ->
                        assertEquals("Client not found with id 5", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
