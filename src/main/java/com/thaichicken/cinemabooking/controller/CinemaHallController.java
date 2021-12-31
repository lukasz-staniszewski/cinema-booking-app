package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.model.CinemaHallEntity;
import com.thaichicken.cinemabooking.service.DefaultCinemaHallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema_halls")
public class CinemaHallController {

    private final DefaultCinemaHallService cinemaHallService;

    public CinemaHallController(DefaultCinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping
    public List<CinemaHallEntity> getAllCinemaHalls() {
        return cinemaHallService.getAllCinemaHalls();
    }

    @GetMapping("/cinema_hall/{id}")
    public CinemaHallEntity getCinemaHall(@PathVariable(value = "id") Integer id) {
        return cinemaHallService.getCinemaHallById(id);
    }

    @PostMapping("/cinema_hall")
    public CinemaHallEntity createCinemaHall(@RequestBody CinemaHallEntity cinemaHall) {
        return cinemaHallService.createCinemaHall(cinemaHall);
    }

    @PutMapping("/cinema_hall/{id}")
    public CinemaHallEntity updateCinemaHall(@PathVariable(value = "id") Integer id,
                                             @RequestBody CinemaHallEntity cinemaHall) {
        return cinemaHallService.updateCinemaHall(id, cinemaHall);
    }

    @DeleteMapping("/cinema_hall/{id}")
    public ResponseEntity<HttpStatus> deleteCinemaHall(@PathVariable(value = "id") Integer id) {
        cinemaHallService.deleteCinemaHall(id);
        return ResponseEntity.ok().build();
    }
}
