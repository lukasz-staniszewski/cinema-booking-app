package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.service.DefaultClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private DefaultClientService clientService;

    @GetMapping
    public List<ClientEntity> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/client/{id}")
    public ClientEntity getClient(@PathVariable(value = "id") Integer id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/client")
    public ClientEntity createClient(@RequestBody ClientEntity client) {
        return clientService.createClient(client);
    }

    @PutMapping("/client/{id}")
    public ClientEntity updateClient(@PathVariable(value = "id") Integer id,
                                     @RequestBody ClientEntity client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable(value = "id") Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
