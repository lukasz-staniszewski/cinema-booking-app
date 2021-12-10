package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.model.ReservationEntity;

import java.util.List;

public interface ReservationService {
    ReservationEntity createReservation(ReservationEntity reservation);

    ReservationEntity updateReservation(Integer id, ReservationEntity reservation);

    void deleteReservation(Integer id);

    List<ReservationEntity> getAllReservations();

    ReservationEntity getReservationById(Integer id);
}
