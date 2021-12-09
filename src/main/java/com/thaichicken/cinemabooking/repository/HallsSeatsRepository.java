package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.HallsSeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallsSeatsRepository extends JpaRepository<HallsSeatsEntity, Long> {
}
