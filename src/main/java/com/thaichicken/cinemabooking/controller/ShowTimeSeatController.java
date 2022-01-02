package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.dto.ShowTimeSeatDTO;
import com.thaichicken.cinemabooking.model.HallSeatEntityPK;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntity;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntityPK;
import com.thaichicken.cinemabooking.service.DefaultHallSeatService;
import com.thaichicken.cinemabooking.service.DefaultReservationService;
import com.thaichicken.cinemabooking.service.DefaultShowTimeSeatService;
import com.thaichicken.cinemabooking.service.DefaultShowTimeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("show_time_seats")
public class ShowTimeSeatController {

    private final ModelMapper modelMapper;

    private final DefaultShowTimeService showTimeService;

    private final DefaultHallSeatService hallSeatService;

    private final DefaultReservationService reservationService;

    private final DefaultShowTimeSeatService showTimeSeatService;

    public ShowTimeSeatController(DefaultShowTimeSeatService showTimeSeatService, ModelMapper modelMapper, DefaultShowTimeService showTimeService, DefaultHallSeatService hallSeatService, DefaultReservationService reservationService) {
        this.showTimeSeatService = showTimeSeatService;
        this.modelMapper = modelMapper;
        this.showTimeService = showTimeService;
        this.hallSeatService = hallSeatService;
        this.reservationService = reservationService;
    }

    @GetMapping
    @ResponseBody
    public List<ShowTimeSeatDTO> getAllShowTimeSeats() {
        List<ShowTimeSeatEntity> showTimeSeatEntities = showTimeSeatService.getAllShowTimeSeats();
        return showTimeSeatEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/show_time_seat")
    @ResponseBody
    public ShowTimeSeatDTO getShowTimeSeat(@RequestParam(name = "show_time_id") Integer showTimeId,
                                           @RequestParam(name = "row_number") Integer rowNumber,
                                           @RequestParam(name = "seat_in_row_number") Integer seatInRowNumber,
                                           @RequestParam(name = "cinema_hall_number") Integer cinemaHallNumber) {
        return convertToDto(showTimeSeatService.getShowTimeSeatById(new ShowTimeSeatEntityPK(showTimeId, rowNumber, seatInRowNumber, cinemaHallNumber)));
    }

    @GetMapping("/showtime_id")
    @ResponseBody
    public List<ShowTimeSeatDTO> getShowTimeSeatByShowtimeId(@RequestParam(name = "show_time_id") Integer showTimeId) {
        List<ShowTimeSeatEntity> showTimeSeatEntities = showTimeSeatService.getAllShowTimeSeatsByShowtimeId(showTimeId);
        return showTimeSeatEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/show_time_seat")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ShowTimeSeatDTO createShowTimeSeat(@RequestBody ShowTimeSeatDTO showTimeSeatDto) {
        ShowTimeSeatEntity showTimeSeat = convertToEntity(showTimeSeatDto);
        ShowTimeSeatEntity showTimeSeatCreated = showTimeSeatService.createShowTimeSeat(showTimeSeat);
        return convertToDto(showTimeSeatCreated);
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
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ShowTimeSeatDTO updateShowTimeSeat(@RequestParam(name = "show_time_id") Integer showTimeId,
                                              @RequestParam(name = "row_number") Integer rowNumber,
                                              @RequestParam(name = "seat_in_row_number") Integer seatInRowNumber,
                                              @RequestParam(name = "cinema_hall_number") Integer cinemaHallNumber,
                                              @RequestBody ShowTimeSeatDTO showTimeSeatDto) {
        if (!Objects.equals(new ShowTimeSeatEntityPK(showTimeId, rowNumber, seatInRowNumber, cinemaHallNumber), new ShowTimeSeatEntityPK(showTimeSeatDto.getShowtimeId(), showTimeSeatDto.getRowNumber(), showTimeSeatDto.getSeatInRowNumber(), showTimeSeatDto.getCinemaHallNumber()))) {
            throw new IllegalArgumentException("IDs don't match");
        }
        ShowTimeSeatEntity showTimeSeat = convertToEntity(showTimeSeatDto);
        ShowTimeSeatEntity showTimeSeatUpdated = showTimeSeatService.updateShowTimeSeat(new ShowTimeSeatEntityPK(showTimeId, rowNumber, seatInRowNumber, cinemaHallNumber), showTimeSeat);
        return convertToDto(showTimeSeatUpdated);
    }

    private ShowTimeSeatDTO convertToDto(ShowTimeSeatEntity showTimeSeat) {
        ShowTimeSeatDTO showTimeSeatDTO = modelMapper.map(showTimeSeat, ShowTimeSeatDTO.class);
        showTimeSeatDTO.setReservationId(showTimeSeat.getReservationByReservationId().getReservationId());
        return showTimeSeatDTO;
    }

    private ShowTimeSeatEntity convertToEntity(ShowTimeSeatDTO showTimeSeatDTO) {
        ShowTimeSeatEntity showTimeSeat = modelMapper.map(showTimeSeatDTO, ShowTimeSeatEntity.class);
        showTimeSeat.setShowTimeByShowtimeId(showTimeService.getShowTimeById(showTimeSeatDTO.getShowtimeId()));
        showTimeSeat.setHallSeat(hallSeatService.getHallSeatById(new HallSeatEntityPK(showTimeSeatDTO.getRowNumber(), showTimeSeatDTO.getSeatInRowNumber(), showTimeSeatDTO.getCinemaHallNumber())));
        showTimeSeat.setReservationByReservationId(reservationService.getReservationById(showTimeSeatDTO.getReservationId()));
        return showTimeSeat;
    }
}
