package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceAlreadyExistsException;
import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.ReservationEntity;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntity;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntityPK;
import com.thaichicken.cinemabooking.repository.ShowTimeSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultShowTimeSeatService implements ShowTimeSeatService {

    private final ShowTimeSeatRepository showTimeSeatRepository;

    public DefaultShowTimeSeatService(ShowTimeSeatRepository showTimeSeatRepository) {
        this.showTimeSeatRepository = showTimeSeatRepository;
    }

    @Override
    public ShowTimeSeatEntity createShowTimeSeat(ShowTimeSeatEntity showTimeSeat) {
        ShowTimeSeatEntityPK showTimeSeatEntityPK = new ShowTimeSeatEntityPK(showTimeSeat.getShowtimeId(),
                showTimeSeat.getRowNumber(), showTimeSeat.getSeatInRowNumber(), showTimeSeat.getCinemaHallNumber());
        Optional<ShowTimeSeatEntity> showTimeSeatEntityOptional = showTimeSeatRepository.findById(showTimeSeatEntityPK);
        if (showTimeSeatEntityOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("Show Time Seat already exists with id " + showTimeSeatEntityPK);
        } else {
            return showTimeSeatRepository.save(showTimeSeat);
        }
    }

    @Override
    public ShowTimeSeatEntity updateShowTimeSeat(ShowTimeSeatEntityPK id, ShowTimeSeatEntity showTimeSeat) {
        return showTimeSeatRepository.findById(id)
                .map(showTimeSeat1 -> {
                    showTimeSeat1.setReservationByReservationId(showTimeSeat.getReservationByReservationId());
                    return showTimeSeatRepository.save(showTimeSeat1);
                }).orElseThrow(() -> new ResourceNotFoundException("Show Time Seat not found with id " + id));
    }

    @Override
    public void deleteShowTimeSeat(ShowTimeSeatEntityPK id) {
        ShowTimeSeatEntity showTimeSeat = showTimeSeatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show Time Seat not found with id " + id));
        showTimeSeatRepository.delete(showTimeSeat);
    }

    @Override
    public List<ShowTimeSeatEntity> getAllShowTimeSeats() {
        return showTimeSeatRepository.findAll();
    }

    @Override
    public ShowTimeSeatEntity getShowTimeSeatById(ShowTimeSeatEntityPK id) {
        return showTimeSeatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show Time Seat not found with id " + id));
    }

    public List<ShowTimeSeatEntity> getAllShowTimeSeatsByShowtimeId(Integer showtimeId) {
        return showTimeSeatRepository.findAllByShowtimeId(showtimeId);
    }

    public List<ShowTimeSeatEntity> getAllShowTimeSeatsByReservation(ReservationEntity reservation) {
        return showTimeSeatRepository.findAllByReservationByReservationId(reservation);
    }
}
