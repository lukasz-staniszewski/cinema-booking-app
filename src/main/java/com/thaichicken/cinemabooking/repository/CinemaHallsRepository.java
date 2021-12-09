package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.CinemaHallsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallsRepository extends JpaRepository<CinemaHallsEntity, Long> {
}
