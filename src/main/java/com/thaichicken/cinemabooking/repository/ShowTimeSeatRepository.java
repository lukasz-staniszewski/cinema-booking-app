package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ShowTimeSeatEntity;
import com.thaichicken.cinemabooking.model.ShowTimeSeatEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowTimeSeatRepository extends JpaRepository<ShowTimeSeatEntity, ShowTimeSeatEntityPK> {
    List<ShowTimeSeatEntity> findAllByShowtimeId(Integer showtimeId);
}
