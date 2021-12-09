package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.MoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<MoviesEntity, Long> {
    List<MoviesEntity> findByDirector(String director);
}
