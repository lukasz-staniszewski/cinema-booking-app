package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.model.CinemaHallEntity;

import java.util.List;

public interface CinemaHallService {
    CinemaHallEntity createCinemaHall(CinemaHallEntity cinemaHall);

    CinemaHallEntity updateCinemaHall(Integer id, CinemaHallEntity cinemaHall);

    void deleteCinemaHall(Integer id);

    List<CinemaHallEntity> getAllCinemaHalls();

    CinemaHallEntity getCinemaHallById(Integer id);
}
