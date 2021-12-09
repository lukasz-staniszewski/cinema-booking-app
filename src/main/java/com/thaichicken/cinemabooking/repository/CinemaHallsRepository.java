package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.CinemaHallsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaHallsRepository extends JpaRepository<CinemaHallsEntity, Long> {
}
