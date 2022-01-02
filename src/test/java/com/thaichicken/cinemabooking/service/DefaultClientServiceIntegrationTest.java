package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.ClientRole;
import com.thaichicken.cinemabooking.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DefaultClientServiceIntegrationTest {
    @Autowired
    private DefaultClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        ClientEntity CLIENT_1 = new ClientEntity(1, "Geralt", "ofRivia", "witcher@mail.com", "777666555", "password", ClientRole.USER);
        ClientEntity CLIENT_2 = new ClientEntity(2, "Yenneffer", "ofVengerberg", "yenn@mail.com", "111222333", "password", ClientRole.USER);

        List<ClientEntity> allClients = Arrays.asList(CLIENT_1, CLIENT_2);
        Mockito.when(clientRepository.findById(1)).thenReturn(java.util.Optional.of(CLIENT_1));
        Mockito.when(clientRepository.findById(2)).thenReturn(java.util.Optional.of(CLIENT_2));
        Mockito.when(clientRepository.findAll()).thenReturn(allClients);
    }

    @Test
    public void whenValidClientId_thenClientShouldBeFound() {
        ClientEntity found1 = clientService.getClientById(1);
        ClientEntity found2 = clientService.getClientById(2);
        Assertions.assertEquals(1, found1.getClientId());
        Assertions.assertEquals(2, found2.getClientId());
    }

    @Test
    public void whenInvalidClientId_thenClientShouldNotBeFound() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> clientService.getClientById(3), "Client not found with id 3");
    }

    @Test
    public void whenGetAllClients_thenAllClientsShouldBeFound() {
        List<ClientEntity> foundClients = clientService.getAllClients();
        Assertions.assertEquals(2, foundClients.size());
        Assertions.assertEquals(1, foundClients.get(0).getClientId());
        Assertions.assertEquals(2, foundClients.get(1).getClientId());
    }

    @Test
    public void whenDeleteInvalidClient_thenClientShouldNotBeDeleted() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> clientService.deleteClient(3));
    }

    @Test
    public void whenCreateValidClient_thenClientShouldBeFound() {
        ClientEntity newClient = new ClientEntity("Cirilla", "Riannon", "ciri@mail.com", "999888777", "password", ClientRole.USER);

        Mockito.when(clientRepository.save(newClient)).thenReturn(newClient);
        Mockito.when(clientRepository.findById(3)).thenReturn(java.util.Optional.of(newClient));

        Assertions.assertEquals(clientService.createClient(newClient), clientService.getClientById(3));
    }
}
