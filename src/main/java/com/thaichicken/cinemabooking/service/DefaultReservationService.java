package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.ReservationEntity;
import com.thaichicken.cinemabooking.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultReservationService implements ReservationService {

    private final ReservationRepository reservationRepository;

    public DefaultReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationEntity createReservation(ReservationEntity reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public ReservationEntity updateReservation(Integer id, ReservationEntity reservation) {
        return reservationRepository.findById(id)
                .map(reservation1 -> {
                    reservation1.setTimestamp(reservation.getTimestamp());
                    reservation1.setClientByClientId(reservation.getClientByClientId());
                    return reservationRepository.save(reservation1);
                }).orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    @Override
    public void deleteReservation(Integer id) {
        ReservationEntity reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
        reservationRepository.delete(reservation);
    }

    @Override
    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public ReservationEntity getReservationById(Integer id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    public List<ReservationEntity> getAllReservationsByClient(ClientEntity client) {
        return reservationRepository.findAllByClientByClientId(client);
    }
}
