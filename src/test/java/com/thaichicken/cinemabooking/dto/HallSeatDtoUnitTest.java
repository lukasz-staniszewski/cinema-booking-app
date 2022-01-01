package com.thaichicken.cinemabooking.dto;

import com.thaichicken.cinemabooking.model.HallSeatEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class HallSeatDtoUnitTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertHallSeatEntityToHallSeatDto_thenCorrect() {
        HallSeatEntity hallSeat = new HallSeatEntity();
        hallSeat.setCinemaHallNumber(1);
        hallSeat.setRowNumber(1);
        hallSeat.setSeatInRowNumber(1);

        HallSeatDTO hallSeatDTO = modelMapper.map(hallSeat, HallSeatDTO.class);
        Assertions.assertEquals(hallSeatDTO.getCinemaHallNumber(), hallSeat.getCinemaHallNumber());
        Assertions.assertEquals(hallSeatDTO.getRowNumber(), hallSeat.getRowNumber());
        Assertions.assertEquals(hallSeatDTO.getSeatInRowNumber(), hallSeat.getSeatInRowNumber());
    }

    @Test
    public void whenConvertHallSeatDtoToHallSeatEntity_thenCorrect() {
        HallSeatDTO hallSeatDTO = new HallSeatDTO();
        hallSeatDTO.setCinemaHallNumber(1);
        hallSeatDTO.setRowNumber(1);
        hallSeatDTO.setSeatInRowNumber(1);

        HallSeatEntity hallSeat = modelMapper.map(hallSeatDTO, HallSeatEntity.class);
        Assertions.assertEquals(hallSeatDTO.getCinemaHallNumber(), hallSeat.getCinemaHallNumber());
        Assertions.assertEquals(hallSeatDTO.getRowNumber(), hallSeat.getRowNumber());
        Assertions.assertEquals(hallSeatDTO.getSeatInRowNumber(), hallSeat.getSeatInRowNumber());
    }
}
