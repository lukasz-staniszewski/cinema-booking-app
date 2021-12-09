package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.MoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<MoviesEntity, Long> {
    List<MoviesEntity> findByDirector(String director);
}
