package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.model.HallSeatEntity;
import com.thaichicken.cinemabooking.service.DefaultHallSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hall_seats")
public class HallSeatController {

    @Autowired
    private DefaultHallSeatService hallSeatService;

    @GetMapping
    public List<HallSeatEntity> getAllHallSeats() {
        return hallSeatService.getAllHallSeats();
    }

    @GetMapping("hall_seat/{id}")
    public HallSeatEntity getHallSeat(@PathVariable(value = "id") Integer id) {
        return hallSeatService.getHallSeatById(id);
    }

    @PostMapping("hall_seat")
    public HallSeatEntity createHallSeat(@RequestBody HallSeatEntity hallSeat) {
        return hallSeatService.createHallSeat(hallSeat);
    }

    @PutMapping("hall_seat/{id}")
    public HallSeatEntity updateHallSeat(@PathVariable(value = "id") Integer id,
                                         @RequestBody HallSeatEntity hallSeat) {
        return hallSeatService.updateHallSeat(id, hallSeat);
    }

    @DeleteMapping("hall_seat/{id}")
    public ResponseEntity<HttpStatus> deleteHallSeat(@PathVariable(value = "id") Integer id) {
        hallSeatService.deleteHallSeat(id);
        return ResponseEntity.ok().build();
    }
}
