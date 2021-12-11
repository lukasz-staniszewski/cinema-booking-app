package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.model.ShowTimeSeatEntity;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntityPK;
import com.thaichicken.cinemabooking.service.DefaultShowTimeSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("show_time_seats")
public class ShowTimeSeatController {

    @Autowired
    private DefaultShowTimeSeatService showTimeSeatService;

    @GetMapping
    public List<ShowTimeSeatEntity> getAllShowTimeSeats() {
        return showTimeSeatService.getAllShowTimeSeats();
    }

    @GetMapping("/show_time_seat")
    public ShowTimeSeatEntity getShowTimeSeat(@RequestParam(name = "show_time_id") Integer showTimeId,
                                              @RequestParam(name = "row_number") Integer rowNumber,
                                              @RequestParam(name = "seat_in_row_number") Integer seatInRowNumber,
                                              @RequestParam(name = "cinema_hall_number") Integer cinemaHallNumber) {
        return showTimeSeatService.getShowTimeSeatById(new ShowTimeSeatEntityPK(showTimeId, rowNumber, seatInRowNumber, cinemaHallNumber));
    }

    @PostMapping("/show_time_seat")
    public ShowTimeSeatEntity createShowTimeSeat(@RequestBody ShowTimeSeatEntity showTimeSeat) {
        return showTimeSeatService.createShowTimeSeat(showTimeSeat);
    }

    @DeleteMapping("/show_time_seat")
    public ResponseEntity<HttpStatus> deleteShowTimeSeat(@RequestParam(name = "show_time_id") Integer showTimeId,
                                                         @RequestParam(name = "row_number") Integer rowNumber,
                                                         @RequestParam(name = "seat_in_row_number") Integer seatInRowNumber,
                                                         @RequestParam(name = "cinema_hall_number") Integer cinemaHallNumber) {
        showTimeSeatService.deleteShowTimeSeat(new ShowTimeSeatEntityPK(showTimeId, rowNumber, seatInRowNumber, cinemaHallNumber));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/show_time_seat")
    public ShowTimeSeatEntity updateShowTimeSeat(@RequestParam(name = "show_time_id") Integer showTimeId,
                                                 @RequestParam(name = "row_number") Integer rowNumber,
                                                 @RequestParam(name = "seat_in_row_number") Integer seatInRowNumber,
                                                 @RequestParam(name = "cinema_hall_number") Integer cinemaHallNumber,
                                                 @RequestBody ShowTimeSeatEntity showTimeSeat) {
        return showTimeSeatService.updateShowTimeSeat(new ShowTimeSeatEntityPK(showTimeId, rowNumber, seatInRowNumber, cinemaHallNumber), showTimeSeat);
    }
}
