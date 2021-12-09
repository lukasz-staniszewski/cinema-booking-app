package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ReservationEntity, Long> {
}
