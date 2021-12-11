package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.model.HallSeatEntity;
import com.thaichicken.cinemabooking.model.HallSeatEntityPK;
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

    @GetMapping("/hall_seat")
    public HallSeatEntity getHallSeat(@RequestParam(name = "row_number") Integer row_number,
                                      @RequestParam(name = "seat_in_row_number") Integer seat_in_row_number,
                                      @RequestParam(name = "cinema_hall_number") Integer cinema_hall_number) {
        return hallSeatService.getHallSeatById(new HallSeatEntityPK(row_number, seat_in_row_number, cinema_hall_number));
    }

    @PostMapping("/hall_seat")
    public HallSeatEntity createHallSeat(@RequestBody HallSeatEntity hallSeat) {
        return hallSeatService.createHallSeat(hallSeat);
    }

    @DeleteMapping("/hall_seat")
    public ResponseEntity<HttpStatus> deleteHallSeat(@RequestParam(name = "row_number") Integer row_number,
                                                     @RequestParam(name = "seat_in_row_number") Integer seat_in_row_number,
                                                     @RequestParam(name = "cinema_hall_number") Integer cinema_hall_number) {
        hallSeatService.deleteHallSeat(new HallSeatEntityPK(row_number, seat_in_row_number, cinema_hall_number));
        return ResponseEntity.ok().build();
    }
}
