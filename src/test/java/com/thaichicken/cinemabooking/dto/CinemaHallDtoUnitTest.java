package com.thaichicken.cinemabooking.dto;

import com.thaichicken.cinemabooking.model.CinemaHallEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class CinemaHallDtoUnitTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertCinemaHallEntityToCinemaHallDto_thenCorrect() {
        CinemaHallEntity cinemaHall = new CinemaHallEntity();
        cinemaHall.setCinemaHallNumber(1);
        cinemaHall.setnRows(15);
        cinemaHall.setnSeatsInRows(10);

        CinemaHallDTO cinemaHallDTO = modelMapper.map(cinemaHall, CinemaHallDTO.class);
        Assertions.assertEquals(cinemaHall.getCinemaHallNumber(), cinemaHallDTO.getCinemaHallNumber());
        Assertions.assertEquals(cinemaHall.getnRows(), cinemaHallDTO.getNRows());
        Assertions.assertEquals(cinemaHall.getnSeatsInRows(), cinemaHallDTO.getNSeatsInRows());
    }

    @Test
    public void whenConvertCinemaHallDtoToCinemaHallEntity_thenCorrect() {
        CinemaHallDTO cinemaHallDTO = new CinemaHallDTO();
        cinemaHallDTO.setCinemaHallNumber(1);
        cinemaHallDTO.setNRows(15);
        cinemaHallDTO.setNSeatsInRows(10);

        CinemaHallEntity cinemaHall = modelMapper.map(cinemaHallDTO, CinemaHallEntity.class);
        Assertions.assertEquals(cinemaHall.getCinemaHallNumber(), cinemaHallDTO.getCinemaHallNumber());
        Assertions.assertEquals(cinemaHall.getnRows(), cinemaHallDTO.getNRows());
        Assertions.assertEquals(cinemaHall.getnSeatsInRows(), cinemaHallDTO.getNSeatsInRows());
    }
}
