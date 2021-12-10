package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.CinemaHallEntity;
import com.thaichicken.cinemabooking.repository.CinemaHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCinemaHallService implements CinemaHallService {

    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @Override
    public CinemaHallEntity createCinemaHall(CinemaHallEntity cinemaHall) {
        return cinemaHallRepository.save(cinemaHall);
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
