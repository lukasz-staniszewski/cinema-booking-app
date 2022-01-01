package com.thaichicken.cinemabooking.dto;

import com.thaichicken.cinemabooking.model.ClientEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class ClientDtoUnitTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertClientEntityToClientDto_thenCorrect() {
        ClientEntity client = new ClientEntity();
        client.setClientId(1);
        client.setName("Test");
        client.setSurname("Test");
        client.setEmail("test@test.com");

        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
        Assertions.assertEquals(clientDTO.getClientId(), client.getClientId());
        Assertions.assertEquals(clientDTO.getName(), client.getName());
        Assertions.assertEquals(clientDTO.getSurname(), client.getSurname());
        Assertions.assertEquals(clientDTO.getEmail(), client.getEmail());
    }
}
