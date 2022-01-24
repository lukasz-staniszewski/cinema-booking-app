package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.dto.ClientDTO;
import com.thaichicken.cinemabooking.dto.ClientProfileDataDTO;
import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.service.DefaultClientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ModelMapper modelMapper;

    private final DefaultClientService clientService;

    public ClientController(DefaultClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public List<ClientDTO> getAllClients() {
        List<ClientEntity> clientEntities = clientService.getAllClients();

        log.info("All clients has been get");
        return clientEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/client/{id}")
    @ResponseBody
    public ClientDTO getClient(@PathVariable(value = "id") Integer id) {
        log.info("Client with id: " + id + " has been get");
        return convertToDto(clientService.getClientById(id));
    }

    @GetMapping("/client/email")
    @ResponseBody
    public ClientProfileDataDTO getClientByEmail(@RequestParam(value = "email") String email) {
        log.info("Client with email: " + email + " has been get");
        return convertToProfileDataDto(clientService.getClientByEmail(email));
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ClientDTO createClient(@RequestBody ClientEntity client) {
        log.info("Client with email: " + client.getEmail() + " has been created");
        return convertToDto(clientService.createClient(client));
    }

    @PutMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientDTO updateClient(@PathVariable(value = "id") Integer id,
                                  @RequestBody ClientEntity client) {
        log.info("Client with email: " + client.getEmail() + " has been updated");
        return convertToDto(clientService.updateClient(id, client));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable(value = "id") Integer id) {
        clientService.deleteClient(id);
        log.info("Client with id: " + id + " has been deleted");
        return ResponseEntity.ok().build();
    }

    private ClientDTO convertToDto(ClientEntity client) {
        return modelMapper.map(client, ClientDTO.class);

    }

    private ClientProfileDataDTO convertToProfileDataDto(ClientEntity client) {
        return modelMapper.map(client, ClientProfileDataDTO.class);
    }

    private ClientEntity convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, ClientEntity.class);
    }
}
