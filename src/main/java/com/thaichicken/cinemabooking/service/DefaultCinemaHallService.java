package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceAlreadyExistsException;
import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.CinemaHallEntity;
import com.thaichicken.cinemabooking.repository.CinemaHallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCinemaHallService implements CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;

    public DefaultCinemaHallService(CinemaHallRepository cinemaHallRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
    }

    @Override
    public CinemaHallEntity createCinemaHall(CinemaHallEntity cinemaHall) {
        Optional<CinemaHallEntity> cinemaHallEntityOptional = cinemaHallRepository.findById(cinemaHall.getCinemaHallNumber());
        if (cinemaHallEntityOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("Cinema Hall already exists with id " + cinemaHall.getCinemaHallNumber());
        } else {
            return cinemaHallRepository.save(cinemaHall);
        }
    }

    @Override
    public CinemaHallEntity updateCinemaHall(Integer id, CinemaHallEntity cinemaHall) {
        return cinemaHallRepository.findById(id)
                .map(cinemaHall1 -> {
                    cinemaHall1.setCapacity(cinemaHall.getCapacity());
                    cinemaHall1.setnRows(cinemaHall.getnRows());
                    cinemaHall1.setnSeatsInRows(cinemaHall.getnSeatsInRows());
                    return cinemaHallRepository.save(cinemaHall1);
                }).orElseThrow(() -> new ResourceNotFoundException("Cinema Hall not found with id " + id));
    }

    @Override
    public void deleteCinemaHall(Integer id) {
        CinemaHallEntity cinemaHall = cinemaHallRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cinema Hall not found with id " + id));
        cinemaHallRepository.delete(cinemaHall);
    }

    @Override
    public List<CinemaHallEntity> getAllCinemaHalls() {
        return cinemaHallRepository.findAll();
    }

    @Override
    public CinemaHallEntity getCinemaHallById(Integer id) {
        return cinemaHallRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cinema Hall not found with id " + id));
    }
}
