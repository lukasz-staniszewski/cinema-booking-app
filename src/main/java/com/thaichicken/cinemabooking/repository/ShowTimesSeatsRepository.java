package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ShowTimesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimesSeatsRepository extends JpaRepository<ShowTimesEntity, Long> {
}
