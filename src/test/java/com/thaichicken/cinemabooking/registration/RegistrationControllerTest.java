package com.thaichicken.cinemabooking.registration;


import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.ClientEntityService;
import com.thaichicken.cinemabooking.model.ClientRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private RegistrationService registrationService;

    @MockBean
    private ClientEntityService clientService;

    @Test
    public void registrationSuccess() throws Exception{
        String registrationStr = "{\"name\":\"Jaskier\",\"surname\":\"Jaskier\",\"email\":\"jaskier@mail.com\",\"phone\":\"999888777\",\"password\":\"password\"}";
        ClientEntity record = new ClientEntity("Jaskier", "Jaskier", "jaskier@mail.com", "999888777", "password", ClientRole.USER);
        Mockito.when(clientService.signUpClient(record)).thenReturn("12345");

        MockHttpServletRequestBuilder mockRequest = post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(registrationStr);


        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void registrationFailure() throws Exception{
        String registrationStr = "{\"name\":\"Jaskier\",\"surname\":\"Jaskier\",\"email\":\"jaskier@mail.com\",\"password\":\"password\"}";

        MockHttpServletRequestBuilder mockRequest = post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(registrationStr);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
}
