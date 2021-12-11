package com.thaichicken.cinemabooking.service;


import com.thaichicken.cinemabooking.model.ShowTimeSeatEntity;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntityPK;

import java.util.List;

public interface ShowTimeSeatService {
    ShowTimeSeatEntity createShowTimeSeat(ShowTimeSeatEntity showTimeSeat);

    ShowTimeSeatEntity updateShowTimeSeat(ShowTimeSeatEntityPK id, ShowTimeSeatEntity showTimeSeat);

    void deleteShowTimeSeat(ShowTimeSeatEntityPK id);

    List<ShowTimeSeatEntity> getAllShowTimeSeats();

    ShowTimeSeatEntity getShowTimeSeatById(ShowTimeSeatEntityPK id);
}
