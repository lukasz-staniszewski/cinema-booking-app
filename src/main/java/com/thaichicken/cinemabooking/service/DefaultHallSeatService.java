package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceAlreadyExistsException;
import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.HallSeatEntity;
import com.thaichicken.cinemabooking.model.HallSeatEntityPK;
import com.thaichicken.cinemabooking.repository.HallSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultHallSeatService implements HallSeatService {

    @Autowired
    private HallSeatRepository hallSeatRepository;

    @Override
    public HallSeatEntity createHallSeat(HallSeatEntity hallSeat) {
        HallSeatEntityPK hallSeatEntityPK = new HallSeatEntityPK(hallSeat.getRowNumber(),
                hallSeat.getSeatInRowNumber(), hallSeat.getCinemaHallNumber());
        Optional<HallSeatEntity> hallSeatEntityOptional = hallSeatRepository.findById(hallSeatEntityPK);
        if (hallSeatEntityOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("Hall seat already exists with id " + hallSeatEntityPK);
        } else {
            return hallSeatRepository.save(hallSeat);
        }
    }

    @Override
    public void deleteHallSeat(HallSeatEntityPK id) {
        HallSeatEntity hallSeat = hallSeatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hall Seat not found with id " + id));
        hallSeatRepository.delete(hallSeat);
    }

    @Override
    public List<HallSeatEntity> getAllHallSeats() {
        return hallSeatRepository.findAll();
    }

    @Override
    public HallSeatEntity getHallSeatById(HallSeatEntityPK id) {
        return hallSeatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hall Seat not found with id " + id));
    }
}
