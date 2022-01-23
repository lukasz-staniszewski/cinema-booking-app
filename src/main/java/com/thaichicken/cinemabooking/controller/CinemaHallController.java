package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.dto.CinemaHallDTO;
import com.thaichicken.cinemabooking.model.CinemaHallEntity;
import com.thaichicken.cinemabooking.service.DefaultCinemaHallService;
import org.modelmapper.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("cinema_halls")
public class CinemaHallController {

    private final ModelMapper modelMapper;

    private final DefaultCinemaHallService cinemaHallService;

    public CinemaHallController(DefaultCinemaHallService cinemaHallService, ModelMapper modelMapper) {
        this.cinemaHallService = cinemaHallService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public List<CinemaHallDTO> getAllCinemaHalls() {
        List<CinemaHallEntity> cinemaHallEntities = cinemaHallService.getAllCinemaHalls();
        return cinemaHallEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/cinema_hall/{id}")
    @ResponseBody
    public CinemaHallDTO getCinemaHall(@PathVariable(value = "id") Integer id) {
        log.info("Cinema with id: " + id + "want to be get");
        return convertToDto(cinemaHallService.getCinemaHallById(id));
    }

    @PostMapping("/cinema_hall")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CinemaHallDTO createCinemaHall(@RequestBody CinemaHallDTO cinemaHallDto) {

        CinemaHallEntity cinemaHall = convertToEntity(cinemaHallDto);
        CinemaHallEntity cinemaHallCreated = cinemaHallService.createCinemaHall(cinemaHall);
        log.info("Cinema with cinema hall number: " + cinemaHallDto.getCinemaHallNumber() + " has been created");
        return convertToDto(cinemaHallCreated);
    }

    @PutMapping("/cinema_hall/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CinemaHallDTO updateCinemaHall(@PathVariable(value = "id") Integer id,
                                          @RequestBody CinemaHallDTO cinemaHallDto) {
        if (!Objects.equals(id, cinemaHallDto.getCinemaHallNumber())) {
            throw new IllegalArgumentException("IDs don't match");
        }

        CinemaHallEntity cinemaHall = convertToEntity(cinemaHallDto);
        CinemaHallEntity cinemaHallUpdated = cinemaHallService.updateCinemaHall(id, cinemaHall);
        log.info("Cinema with id: " + id + "gets update");
        return convertToDto(cinemaHallUpdated);
    }

    @DeleteMapping("/cinema_hall/{id}")
    public ResponseEntity<HttpStatus> deleteCinemaHall(@PathVariable(value = "id") Integer id) {
        cinemaHallService.deleteCinemaHall(id);
        log.info("Cinema with id: " + id + " has been deleted");
        return ResponseEntity.ok().build();
    }

    private CinemaHallDTO convertToDto(CinemaHallEntity cinemaHall) {
        return modelMapper.map(cinemaHall, CinemaHallDTO.class);
    }

    private CinemaHallEntity convertToEntity(CinemaHallDTO cinemaHallDTO) {
        return modelMapper.map(cinemaHallDTO, CinemaHallEntity.class);
    }
}
