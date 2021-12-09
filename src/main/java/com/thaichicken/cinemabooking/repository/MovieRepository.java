package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findByDirector(String director);
}
