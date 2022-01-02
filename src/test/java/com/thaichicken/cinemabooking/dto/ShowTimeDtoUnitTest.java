package com.thaichicken.cinemabooking.dto;

import com.thaichicken.cinemabooking.model.ShowTimeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Time;


public class ShowTimeDtoUnitTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertShowTimeEntityToShowTimeDto_thenCorrect() {
        ShowTimeEntity showTime = new ShowTimeEntity();
        showTime.setShowtimeId(1);
        showTime.setDate(Date.valueOf("2021-12-31"));
        showTime.setHour(Time.valueOf("20:20:00"));

        ShowTimeDTO showTimeDTO = modelMapper.map(showTime, ShowTimeDTO.class);
        Assertions.assertEquals(showTimeDTO.getShowtimeId(), showTime.getShowtimeId());
        Assertions.assertEquals(showTimeDTO.getDate(), showTime.getDate());
        Assertions.assertEquals(showTimeDTO.getHour(), showTime.getHour());
    }

    @Test
    public void whenConvertShowTimeDtoToShowTimeEntity_thenCorrect() {
        ShowTimeDTO showTimeDTO = new ShowTimeDTO();
        showTimeDTO.setShowtimeId(1);
        showTimeDTO.setDate(Date.valueOf("2021-12-31"));
        showTimeDTO.setHour(Time.valueOf("20:20:00"));

        ShowTimeEntity showTime = modelMapper.map(showTimeDTO, ShowTimeEntity.class);
        Assertions.assertEquals(showTimeDTO.getShowtimeId(), showTime.getShowtimeId());
        Assertions.assertEquals(showTimeDTO.getDate(), showTime.getDate());
        Assertions.assertEquals(showTimeDTO.getHour(), showTime.getHour());

    }
}
