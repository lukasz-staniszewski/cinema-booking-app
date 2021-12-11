package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.model.HallSeatEntity;
import com.thaichicken.cinemabooking.model.HallSeatEntityPK;

import java.util.List;

public interface HallSeatService {
    HallSeatEntity createHallSeat(HallSeatEntity hallSeat);

//    HallSeatEntity updateHallSeat(HallSeatEntityPK id, HallSeatEntity hallSeat);

    void deleteHallSeat(HallSeatEntityPK id);

    List<HallSeatEntity> getAllHallSeats();

    HallSeatEntity getHallSeatById(HallSeatEntityPK id);
}
