package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.model.HallSeatEntity;

import java.util.List;

public interface HallSeatService {
    HallSeatEntity createHallSeat(HallSeatEntity hallSeat);

    HallSeatEntity updateHallSeat(Integer id, HallSeatEntity hallSeat);

    void deleteHallSeat(Integer id);

    List<HallSeatEntity> getAllHallSeats();

    HallSeatEntity getHallSeatById(Integer id);
}
