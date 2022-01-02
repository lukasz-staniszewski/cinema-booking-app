package com.thaichicken.cinemabooking.controller;


import com.thaichicken.cinemabooking.dto.HallSeatDTO;
import com.thaichicken.cinemabooking.dto.ReservationCreationDTO;
import com.thaichicken.cinemabooking.dto.ReservationDTO;
import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.HallSeatEntityPK;
import com.thaichicken.cinemabooking.model.ReservationEntity;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntity;
import com.thaichicken.cinemabooking.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final ModelMapper modelMapper;

    private final DefaultClientService clientService;

    private final DefaultReservationService reservationService;

    private final DefaultShowTimeService showTimeService;

    private final DefaultHallSeatService hallSeatService;

    private final DefaultShowTimeSeatService showTimeSeatService;

    public ReservationController(DefaultReservationService reservationService, ModelMapper modelMapper, DefaultClientService clientService, DefaultShowTimeService showTimeService, DefaultHallSeatService hallSeatService, DefaultShowTimeSeatService showTimeSeatService) {
        this.reservationService = reservationService;
        this.modelMapper = modelMapper;
        this.clientService = clientService;
        this.showTimeService = showTimeService;
        this.hallSeatService = hallSeatService;
        this.showTimeSeatService = showTimeSeatService;
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

        for (HallSeatDTO hallSeatDTO : reservationDto.getReservationSeats()) {
            ShowTimeSeatEntity showTimeSeat = new ShowTimeSeatEntity();
            showTimeSeat.setShowtimeId(reservationDto.getShowTimeId());
            showTimeSeat.setRowNumber(hallSeatDTO.getRowNumber());
            showTimeSeat.setSeatInRowNumber(hallSeatDTO.getSeatInRowNumber());
            showTimeSeat.setCinemaHallNumber(hallSeatDTO.getCinemaHallNumber());
            showTimeSeat.setShowTimeByShowtimeId(showTimeService.getShowTimeById(reservationDto.getShowTimeId()));
            showTimeSeat.setHallSeat(hallSeatService.getHallSeatById(new HallSeatEntityPK(hallSeatDTO.getRowNumber(), hallSeatDTO.getSeatInRowNumber(), hallSeatDTO.getCinemaHallNumber())));
            showTimeSeat.setReservationByReservationId(reservationService.getReservationById(reservationCreated.getReservationId()));
            showTimeSeatService.createShowTimeSeat(showTimeSeat);
        }
        return convertToDto(reservationCreated);
    }

//    @PutMapping("/reservation/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ReservationDTO updateReservation(@PathVariable(value = "id") Integer id,
//                                            @RequestBody ReservationCreationDTO
//                                                    reservationDto) {
//        if (!Objects.equals(id, reservationDto.getReservationId())) {
//            throw new IllegalArgumentException("IDS don't match");
//        }
//        ReservationEntity reservation = convertToEntity(reservationDto);
//        ReservationEntity reservationUpdated = reservationService.updateReservation(id, reservation);
//
//        for (HallSeatDTO hallSeatDTO: reservationDto.getReservationSeats()) {
//            ShowTimeSeatEntity showTimeSeat = new ShowTimeSeatEntity();
//            showTimeSeat.setShowtimeId(reservationDto.getShowTimeId());
//            showTimeSeat.setRowNumber(hallSeatDTO.getRowNumber());
//            showTimeSeat.setSeatInRowNumber(hallSeatDTO.getSeatInRowNumber());
//            showTimeSeat.setCinemaHallNumber(hallSeatDTO.getCinemaHallNumber());
//            showTimeSeat.setShowTimeByShowtimeId(showTimeService.getShowTimeById(reservationDto.getShowTimeId()));
//            showTimeSeat.setHallSeat(hallSeatService.getHallSeatById(new HallSeatEntityPK(hallSeatDTO.getRowNumber(), hallSeatDTO.getSeatInRowNumber(), hallSeatDTO.getCinemaHallNumber())));
//            showTimeSeat.setReservationByReservationId(reservationService.getReservationById(reservationUpdated.getReservationId()));
//            showTimeSeatService.updateShowTimeSeat(showTimeSeat);
//        }
//        return convertToDto(reservationUpdated);
//    }

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
        ReservationEntity reservation = new ReservationEntity();
        reservation.setTimestamp(reservationCreationDTO.getTimestamp());
        reservation.setClientByClientId(clientService.getClientByEmail(reservationCreationDTO.getClientEmail()));
        return reservation;
    }
}
