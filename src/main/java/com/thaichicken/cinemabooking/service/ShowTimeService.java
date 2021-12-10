package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.model.ShowTimeEntity;

import java.util.List;

public interface ShowTimeService {
    ShowTimeEntity createShowTime(ShowTimeEntity showTime);

    ShowTimeEntity updateShowTime(Integer id, ShowTimeEntity showTime);

    void deleteShowTime(Integer id);

    List<ShowTimeEntity> getAllShowTimes();

    ShowTimeEntity getShowTimeById(Integer id);
}
