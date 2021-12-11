package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ShowTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTimeEntity, Integer> {
    List<ShowTimeEntity> getAllByDate(Date dateDay);
}
