package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.ClientRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void whenFindId_thenReturnClient() {
        ClientEntity CLIENT_1 = new ClientEntity("Geralt", "ofRivia", "witcher@mail.com", "777666555", "password", ClientRole.USER);
        entityManager.persistAndFlush(CLIENT_1);

        Optional<ClientEntity> found = clientRepository.findById(CLIENT_1.getClientId());
        found.ifPresent(clientEntity -> Assertions.assertEquals(clientEntity.getName(), CLIENT_1.getName()));

    }

    @Test
    public void whenValidEmail_thenReturnClient() {
        ClientEntity CLIENT_1 = new ClientEntity("Geralt", "ofRivia", "witcher@mail.com", "777666555", "password", ClientRole.USER);
        entityManager.persistAndFlush(CLIENT_1);

        Optional<ClientEntity> found = clientRepository.findByEmail(CLIENT_1.getEmail());
        found.ifPresent(clientEntity -> Assertions.assertEquals(clientEntity.getName(), CLIENT_1.getName()));
    }

    @Test
    public void whenValidClient_thenAdd() {
        ClientEntity CLIENT_1 = new ClientEntity("Geralt", "ofRivia", "witcher@mail.com", "777666555", "password", ClientRole.USER);
        entityManager.persistAndFlush(CLIENT_1);

        ClientEntity createdClient = clientRepository.saveAndFlush(CLIENT_1);
        Assertions.assertEquals(createdClient.getName(), CLIENT_1.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Optional<ClientEntity> found = clientRepository.findById(-2);
        found.ifPresent(Assertions::assertNull);
    }
}
