package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.CinemaHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHallEntity, Long> {
}
