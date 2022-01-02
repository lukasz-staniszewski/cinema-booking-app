package com.thaichicken.cinemabooking.controller;


import com.thaichicken.cinemabooking.dto.ReservationCreationDTO;
import com.thaichicken.cinemabooking.dto.ReservationDTO;
import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.ReservationEntity;
import com.thaichicken.cinemabooking.service.DefaultClientService;
import com.thaichicken.cinemabooking.service.DefaultReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final ModelMapper modelMapper;

    private final DefaultClientService clientService;

    private final DefaultReservationService reservationService;

    public ReservationController(DefaultReservationService reservationService, ModelMapper modelMapper, DefaultClientService clientService) {
        this.reservationService = reservationService;
        this.modelMapper = modelMapper;
        this.clientService = clientService;
    }

    @GetMapping
    @ResponseBody
    public List<ReservationDTO> getAllReservations() {
        List<ReservationEntity> reservationEntities = reservationService.getAllReservations();
        return reservationEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/reservation/{id}")
    @ResponseBody
    public ReservationDTO getReservation(@PathVariable(value = "id") Integer id) {
        return convertToDto(reservationService.getReservationById(id));
    }

    @PostMapping("/reservation")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ReservationDTO createReservation(@RequestBody ReservationCreationDTO reservationDto) {
        ReservationEntity reservation = convertToEntity(reservationDto);
        ReservationEntity reservationCreated = reservationService.createReservation(reservation);
        return convertToDto(reservationCreated);
    }

    @PutMapping("/reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ReservationDTO updateReservation(@PathVariable(value = "id") Integer id,
                                            @RequestBody ReservationCreationDTO
                                                    reservationDto) {
        if (!Objects.equals(id, reservationDto.getReservationId())) {
            throw new IllegalArgumentException("IDS don't match");
        }
        ReservationEntity reservation = convertToEntity(reservationDto);
        ReservationEntity reservationUpdated = reservationService.updateReservation(id, reservation);
        return convertToDto(reservationUpdated);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable(value = "id") Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    private ReservationDTO convertToDto(ReservationEntity reservation) {
        ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);
        reservationDTO.setClientId(reservation.getClientByClientId().getClientId());
        reservationDTO.setClientName(reservation.getClientByClientId().getName());
        reservationDTO.setClientSurname(reservation.getClientByClientId().getSurname());
        reservationDTO.setClientEmail(reservation.getClientByClientId().getEmail());
        reservationDTO.setClientPhone(reservation.getClientByClientId().getPhone());
        return reservationDTO;
    }

    private ReservationEntity convertToEntity(ReservationDTO reservationDTO) {
        ReservationEntity reservation = modelMapper.map(reservationDTO, ReservationEntity.class);
        ClientEntity client = clientService.getClientById(reservationDTO.getClientId());
        reservation.setClientByClientId(client);
        return reservation;
    }

    private ReservationEntity convertToEntity(ReservationCreationDTO reservationCreationDTO) {
        ReservationEntity reservation = modelMapper.map(reservationCreationDTO, ReservationEntity.class);
        ClientEntity client = clientService.getClientById(reservationCreationDTO.getClientId());
        reservation.setClientByClientId(client);
        return reservation;
    }
}
