package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.model.ClientEntity;

import java.util.List;

public interface ClientService {
    ClientEntity createClient(ClientEntity client);

    ClientEntity updateClient(Integer id, ClientEntity client);

    void deleteClient(Integer id);

    List<ClientEntity> getAllClients();

    ClientEntity getClientById(Integer id);

}
