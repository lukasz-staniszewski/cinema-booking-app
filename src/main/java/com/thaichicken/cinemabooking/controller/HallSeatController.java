package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.dto.HallSeatDTO;
import com.thaichicken.cinemabooking.model.HallSeatEntity;
import com.thaichicken.cinemabooking.model.HallSeatEntityPK;
import com.thaichicken.cinemabooking.service.DefaultCinemaHallService;
import com.thaichicken.cinemabooking.service.DefaultHallSeatService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("hall_seats")
public class HallSeatController {

    private final ModelMapper modelMapper;

    private final DefaultCinemaHallService cinemaHallService;

    private final DefaultHallSeatService hallSeatService;

    public HallSeatController(DefaultHallSeatService hallSeatService, ModelMapper modelMapper, DefaultCinemaHallService cinemaHallService) {
        this.hallSeatService = hallSeatService;
        this.modelMapper = modelMapper;
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping
    @ResponseBody
    public List<HallSeatDTO> getAllHallSeats() {
        List<HallSeatEntity> hallSeatEntities = hallSeatService.getAllHallSeats();
        log.info("All hall seats has been get");
        return hallSeatEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/hall_seat")
    @ResponseBody
    public HallSeatDTO getHallSeat(@RequestParam(name = "row_number") Integer row_number,
                                   @RequestParam(name = "seat_in_row_number") Integer seat_in_row_number,
                                   @RequestParam(name = "cinema_hall_number") Integer cinema_hall_number) {
        log.info("Hall seat with id: " + hallSeatService.getHallSeatById(new HallSeatEntityPK(row_number, seat_in_row_number, cinema_hall_number)) + " has been get");
        return convertToDto(hallSeatService.getHallSeatById(new HallSeatEntityPK(row_number, seat_in_row_number, cinema_hall_number)));
    }

    @PostMapping("/hall_seat")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public HallSeatDTO createHallSeat(@RequestBody HallSeatDTO hallSeatDto) {
        HallSeatEntity hallSeat = convertToEntity(hallSeatDto);
        HallSeatEntity hallSeatCreated = hallSeatService.createHallSeat(hallSeat);
        log.info("Hall seat for: " + hallSeatDto.getCinemaHallNumber() + " in row: " + hallSeatDto.getRowNumber() + " with number: " + hallSeatDto.getSeatInRowNumber() + " has been created");
        return convertToDto(hallSeatCreated);
    }


    @DeleteMapping("/hall_seat")
    public ResponseEntity<HttpStatus> deleteHallSeat(@RequestParam(name = "row_number") Integer row_number,
                                                     @RequestParam(name = "seat_in_row_number") Integer seat_in_row_number,
                                                     @RequestParam(name = "cinema_hall_number") Integer cinema_hall_number) {
        hallSeatService.deleteHallSeat(new HallSeatEntityPK(row_number, seat_in_row_number, cinema_hall_number));
        log.info("Hall seat for: " + cinema_hall_number + " in row: " + row_number + " with number: " + seat_in_row_number + " has been created");
        return ResponseEntity.ok().build();
    }

    private HallSeatDTO convertToDto(HallSeatEntity hallSeat) {
        return modelMapper.map(hallSeat, HallSeatDTO.class);
    }

    private HallSeatEntity convertToEntity(HallSeatDTO hallSeatDTO) {
        HallSeatEntity hallSeat = modelMapper.map(hallSeatDTO, HallSeatEntity.class);
        hallSeat.setCinemaHallByCinemaHallNumber(cinemaHallService.getCinemaHallById(hallSeat.getCinemaHallNumber()));
        return hallSeat;
    }
}
