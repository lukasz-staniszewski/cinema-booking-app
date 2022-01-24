package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.dto.ShowTimeDTO;
import com.thaichicken.cinemabooking.model.ShowTimeEntity;
import com.thaichicken.cinemabooking.service.DefaultCinemaHallService;
import com.thaichicken.cinemabooking.service.DefaultMovieService;
import com.thaichicken.cinemabooking.service.DefaultShowTimeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("show_times")
public class ShowTimeController {

    private final ModelMapper modelMapper;

    private final DefaultMovieService movieService;

    private final DefaultCinemaHallService cinemaHallService;

    private final DefaultShowTimeService showTimeService;

    public ShowTimeController(DefaultShowTimeService showTimeService, ModelMapper modelMapper, DefaultMovieService movieService, DefaultCinemaHallService cinemaHallService) {
        this.showTimeService = showTimeService;
        this.modelMapper = modelMapper;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping
    @ResponseBody
    public List<ShowTimeDTO> getAllShowTimes() {
        List<ShowTimeEntity> showTimeEntities = showTimeService.getAllShowTimes();
        log.info("Get all Show times");
        return showTimeEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/date")
    @ResponseBody
    public List<ShowTimeDTO> getShowTimesByDate(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<ShowTimeEntity> showTimeEntities = showTimeService.getShowTimesByDate(date);
        log.info("Show time with date: " + date + " has been get");
        return showTimeEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/show_time/{id}")
    @ResponseBody
    public ShowTimeDTO getShowTime(@PathVariable(value = "id") Integer id) {
        log.info("Show time with id: " + id + " has been get");
        return convertToDto(showTimeService.getShowTimeById(id));
    }

    @PostMapping("/show_time")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ShowTimeDTO createShowTime(@RequestBody ShowTimeDTO showTimeDto) {
        ShowTimeEntity showTime = convertToEntity(showTimeDto);
        ShowTimeEntity showTimeCreated = showTimeService.createShowTime(showTime);
        log.info("Show time with name: " + showTimeDto.getName() + " has been created");
        return convertToDto(showTimeCreated);
    }

    @PutMapping("/show_time/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ShowTimeDTO updateShowTime(@PathVariable(value = "id") Integer id,
                                      @RequestBody ShowTimeDTO showTimeDto) {
        if (!Objects.equals(id, showTimeDto.getShowtimeId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        ShowTimeEntity showTime = convertToEntity(showTimeDto);
        ShowTimeEntity showTimeUpdated = showTimeService.updateShowTime(id, showTime);
        log.info("Show time with id: " + id + " has been updated");
        return convertToDto(showTimeUpdated);
    }

    @DeleteMapping("/show_time/{id}")
    public ResponseEntity<HttpStatus> deleteShowTime(@PathVariable(value = "id") Integer id) {
        showTimeService.deleteShowTime(id);
        log.info("Show time with id: " + id + " has been deleted");
        return ResponseEntity.ok().build();
    }

    private ShowTimeDTO convertToDto(ShowTimeEntity showTime) {
        ShowTimeDTO showTimeDTO = modelMapper.map(showTime, ShowTimeDTO.class);
        showTimeDTO.setMovieId(showTime.getMovieByMovieId().getMovieId());
        showTimeDTO.setName(showTime.getMovieByMovieId().getName());
        showTimeDTO.setDescription(showTime.getMovieByMovieId().getDescription());
        showTimeDTO.setLength(showTime.getMovieByMovieId().getLength());
        showTimeDTO.setProductionYear(showTime.getMovieByMovieId().getProductionYear());
        showTimeDTO.setType(showTime.getMovieByMovieId().getType());
        showTimeDTO.setDirector(showTime.getMovieByMovieId().getDirector());
//        showTimeDTO.setCinemaHallNumber(showTime.getCinemaHallByCinemaHallNumber().getCinemaHallNumber());
        return showTimeDTO;
    }

    private ShowTimeEntity convertToEntity(ShowTimeDTO showTimeDTO) {
        ShowTimeEntity showTime = modelMapper.map(showTimeDTO, ShowTimeEntity.class);
        showTime.setMovieByMovieId(movieService.getMovieById(showTimeDTO.getMovieId()));
        showTime.setCinemaHallByCinemaHallNumber(cinemaHallService.getCinemaHallById(showTimeDTO.getCinemaHallNumber()));
        return showTime;
    }
}
