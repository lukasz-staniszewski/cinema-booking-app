package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.HallSeatEntity;
import com.thaichicken.cinemabooking.repository.HallSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultHallSeatService implements HallSeatService {

    @Autowired
    private HallSeatRepository hallSeatRepository;

    @Override
    public HallSeatEntity createHallSeat(HallSeatEntity hallSeat) {
        return hallSeatRepository.save(hallSeat);
    }

    @Override
    public HallSeatEntity updateHallSeat(Integer id, HallSeatEntity hallSeat) {
        return hallSeatRepository.findById(id)
                .map(hallSeat1 -> {
                    hallSeat1.setRowNumber(hallSeat.getRowNumber());
                    hallSeat1.setSeatInRowNumber(hallSeat.getSeatInRowNumber());
                    hallSeat1.setCinemaHallNumber(hallSeat.getCinemaHallNumber());
                    return hallSeatRepository.save(hallSeat1);
                }).orElseThrow(() -> new ResourceNotFoundException("Hall Seat not found with id " + id));
    }

    @Override
    public void deleteHallSeat(Integer id) {
        HallSeatEntity hallSeat = hallSeatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hall Seat not found with id " + id));
        hallSeatRepository.delete(hallSeat);
    }

    @Override
    public List<HallSeatEntity> getAllHallSeats() {
        return hallSeatRepository.findAll();
    }

    @Override
    public HallSeatEntity getHallSeatById(Integer id) {
        return hallSeatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hall Seat not found with id " + id));
    }
}
