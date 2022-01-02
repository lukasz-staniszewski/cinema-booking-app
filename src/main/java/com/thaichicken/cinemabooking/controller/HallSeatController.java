package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.dto.HallSeatDTO;
import com.thaichicken.cinemabooking.model.HallSeatEntity;
import com.thaichicken.cinemabooking.model.HallSeatEntityPK;
import com.thaichicken.cinemabooking.service.DefaultHallSeatService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("hall_seats")
public class HallSeatController {

    private final ModelMapper modelMapper;

    private final DefaultHallSeatService hallSeatService;

    public HallSeatController(DefaultHallSeatService hallSeatService, ModelMapper modelMapper) {
        this.hallSeatService = hallSeatService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public List<HallSeatDTO> getAllHallSeats() {
        List<HallSeatEntity> hallSeatEntities = hallSeatService.getAllHallSeats();
        return hallSeatEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/hall_seat")
    @ResponseBody
    public HallSeatDTO getHallSeat(@RequestParam(name = "row_number") Integer row_number,
                                   @RequestParam(name = "seat_in_row_number") Integer seat_in_row_number,
                                   @RequestParam(name = "cinema_hall_number") Integer cinema_hall_number) {
        return convertToDto(hallSeatService.getHallSeatById(new HallSeatEntityPK(row_number, seat_in_row_number, cinema_hall_number)));
    }

    @PostMapping("/hall_seat")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public HallSeatDTO createHallSeat(@RequestBody HallSeatDTO hallSeatDto) {
        HallSeatEntity hallSeat = convertToEntity(hallSeatDto);
        HallSeatEntity hallSeatCreated = hallSeatService.createHallSeat(hallSeat);
        return convertToDto(hallSeatCreated);
    }


    @DeleteMapping("/hall_seat")
    public ResponseEntity<HttpStatus> deleteHallSeat(@RequestParam(name = "row_number") Integer row_number,
                                                     @RequestParam(name = "seat_in_row_number") Integer seat_in_row_number,
                                                     @RequestParam(name = "cinema_hall_number") Integer cinema_hall_number) {
        hallSeatService.deleteHallSeat(new HallSeatEntityPK(row_number, seat_in_row_number, cinema_hall_number));
        return ResponseEntity.ok().build();
    }

    private HallSeatDTO convertToDto(HallSeatEntity hallSeat) {
        return modelMapper.map(hallSeat, HallSeatDTO.class);
    }

    private HallSeatEntity convertToEntity(HallSeatDTO hallSeatDTO) {
        return modelMapper.map(hallSeatDTO, HallSeatEntity.class);
    }
}
