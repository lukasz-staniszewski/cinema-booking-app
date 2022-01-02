package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultClientService implements ClientService {

    private final ClientRepository clientRepository;

    public DefaultClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientEntity createClient(ClientEntity client) {
        return clientRepository.save(client);
    }

    @Override
    public ClientEntity updateClient(Integer id, ClientEntity client) {
        return clientRepository.findById(id)
                .map(client1 -> {
                    client1.setName(client.getName());
                    client1.setSurname(client.getSurname());
                    client1.setEmail(client.getEmail());
                    client1.setPhone(client.getPhone());
                    return clientRepository.save(client1);
                }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
    }

    @Override
    public void deleteClient(Integer id) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
        clientRepository.delete(client);
    }

    @Override
    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity getClientById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
    }

    public ClientEntity getClientByEmail(String email) {
        return clientRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client not found with email " + email));
    }
}
