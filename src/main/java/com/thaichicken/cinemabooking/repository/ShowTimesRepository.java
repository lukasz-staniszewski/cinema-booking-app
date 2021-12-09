package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ReservationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimesRepository extends JpaRepository<ReservationsEntity, Long> {
}
