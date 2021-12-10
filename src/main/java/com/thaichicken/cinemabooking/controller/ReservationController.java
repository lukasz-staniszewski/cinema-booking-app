package com.thaichicken.cinemabooking.controller;


import com.thaichicken.cinemabooking.model.ReservationEntity;
import com.thaichicken.cinemabooking.service.DefaultReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private DefaultReservationService reservationService;

    @GetMapping
    public List<ReservationEntity> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/reservation/{id}")
    public ReservationEntity getReservation(@PathVariable(value = "id") Integer id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping("/reservation")
    public ReservationEntity createReservation(@RequestBody ReservationEntity reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping("/reservation/{id}")
    public ReservationEntity updateReservation(@PathVariable(value = "id") Integer id,
                                               @RequestBody ReservationEntity reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable(value = "id") Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}
