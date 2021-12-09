package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ShowTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeSeatRepository extends JpaRepository<ShowTimeEntity, Long> {
}
