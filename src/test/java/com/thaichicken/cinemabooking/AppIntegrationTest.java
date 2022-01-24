package com.thaichicken.cinemabooking;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thaichicken.cinemabooking.dto.ClientDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AppIntegrationTest {

    @Value("${server.port}")
    private String port;

    @Test
    public void whenValidReservation_thenShouldCreateNewReservation() throws IOException {
        // Create client
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("test");
        clientDTO.setEmail("test@email.com");
        clientDTO.setSurname("test");
        clientDTO.setPhone("111222333");

        URL url1 = new URL("http://localhost:" + port + "/clients/client/");
        HttpURLConnection con = (HttpURLConnection) url1.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");

        String jsonInputString = "{\"name\":\"" + clientDTO.getName() + "\",\"surname\":\"" + clientDTO.getSurname() + "\",\"email\":\"" + clientDTO.getEmail() + "\",\"phone\":\"" + clientDTO.getPhone() + "\",\"clientRole\":\"USER\"}";
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        String clientId;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonResponse = mapper.readTree(String.valueOf(response));
            clientId = jsonResponse.get("clientId").toString();
        }


        // Get client by email
//        URL url2 = new URL("http://localhost:" + port + "/clients/client/email?email=" + clientDTO.getEmail());
//        HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
//        con2.setRequestMethod("GET");
//
//        StringBuilder content;
//
//        try (BufferedReader in = new BufferedReader(
//                new InputStreamReader(con2.getInputStream()))) {
//
//            String line;
//            content = new StringBuilder();
//
//            while ((line = in.readLine()) != null) {
//
//                content.append(line);
//                content.append(System.lineSeparator());
//            }
//        }

        // Delete client
        URL url3 = new URL("http://localhost:" + port + "/clients/client/" + clientId);
        HttpURLConnection con3 = (HttpURLConnection) url3.openConnection();
        con3.setRequestProperty("Accept", "*/*");
        con3.setRequestMethod("DELETE");

        Assertions.assertEquals(201, con.getResponseCode());
//        Assertions.assertEquals(200, con2.getResponseCode());
        Assertions.assertEquals(200, con3.getResponseCode());
//        Assertions.assertEquals("{\"name\":\"" + clientDTO.getName() + "\",\"surname\":\"" + clientDTO.getSurname() + "\",\"email\":\"" + clientDTO.getEmail() + "\"}", content.toString().strip());
//        Assertions.assertEquals("{\"name\":\"" + clientDTO.getName() + "\",\"surname\":\"" + clientDTO.getSurname() + "\",\"email\":\"" + clientDTO.getEmail() + "\"}", content.toString().strip());
    }
}
