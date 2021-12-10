package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ShowTimeSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeSeatRepository extends JpaRepository<ShowTimeSeatEntity, Integer> {
}
