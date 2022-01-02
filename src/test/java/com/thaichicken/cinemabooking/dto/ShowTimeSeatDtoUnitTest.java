package com.thaichicken.cinemabooking.dto;

import com.thaichicken.cinemabooking.model.HallSeatEntity;
import com.thaichicken.cinemabooking.model.ReservationEntity;
import com.thaichicken.cinemabooking.model.ShowTimeEntity;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ShowTimeSeatDtoUnitTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertShowTimeSeatEntityToShowTimeSeatDto_thenCorrect() {
        ShowTimeSeatEntity showTimeSeat = new ShowTimeSeatEntity();
        showTimeSeat.setShowtimeId(1);
        showTimeSeat.setRowNumber(1);
        showTimeSeat.setSeatInRowNumber(1);
        showTimeSeat.setCinemaHallNumber(1);
        ShowTimeEntity showTime = new ShowTimeEntity();
        showTime.setShowtimeId(1);
        showTime.setDate(Date.valueOf("2021-12-31"));
        showTime.setHour(Time.valueOf("20:20:00"));
        showTimeSeat.setShowTimeByShowtimeId(showTime);
        HallSeatEntity hallSeat = new HallSeatEntity();
        hallSeat.setCinemaHallNumber(1);
        hallSeat.setRowNumber(1);
        hallSeat.setSeatInRowNumber(1);
        showTimeSeat.setHallSeat(hallSeat);
        ReservationEntity reservation = new ReservationEntity();
        reservation.setReservationId(1);
        reservation.setTimestamp(Timestamp.valueOf("2022-01-01 00:00:00"));
        showTimeSeat.setReservationByReservationId(reservation);

        ShowTimeSeatDTO showTimeSeatDTO = modelMapper.map(showTimeSeat, ShowTimeSeatDTO.class);
        Assertions.assertEquals(showTimeSeatDTO.getShowtimeId(), showTimeSeat.getShowtimeId());
        Assertions.assertEquals(showTimeSeatDTO.getRowNumber(), showTimeSeat.getRowNumber());
        Assertions.assertEquals(showTimeSeatDTO.getSeatInRowNumber(), showTimeSeat.getSeatInRowNumber());
        Assertions.assertEquals(showTimeSeatDTO.getCinemaHallNumber(), showTimeSeat.getCinemaHallNumber());
        Assertions.assertEquals(showTimeSeatDTO.getReservationId(), showTimeSeat.getReservationByReservationId().getReservationId());
    }

    @Test
    public void whenConvertShowTimeSeatDtoToShowTimeSeatEntity_thenCorrect() {
        ShowTimeSeatDTO showTimeSeatDTO = new ShowTimeSeatDTO();
        showTimeSeatDTO.setShowtimeId(1);
        showTimeSeatDTO.setRowNumber(1);
        showTimeSeatDTO.setSeatInRowNumber(1);
        showTimeSeatDTO.setCinemaHallNumber(1);
        showTimeSeatDTO.setReservationId(1);

        ShowTimeSeatEntity showTimeSeat = modelMapper.map(showTimeSeatDTO, ShowTimeSeatEntity.class);
        Assertions.assertEquals(showTimeSeatDTO.getShowtimeId(), showTimeSeat.getShowtimeId());
        Assertions.assertEquals(showTimeSeatDTO.getRowNumber(), showTimeSeat.getRowNumber());
        Assertions.assertEquals(showTimeSeatDTO.getSeatInRowNumber(), showTimeSeat.getSeatInRowNumber());
        Assertions.assertEquals(showTimeSeatDTO.getCinemaHallNumber(), showTimeSeat.getCinemaHallNumber());
    }
}
