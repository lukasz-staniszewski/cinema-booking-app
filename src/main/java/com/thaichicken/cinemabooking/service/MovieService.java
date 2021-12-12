package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.model.MovieEntity;

import java.util.List;

public interface MovieService {
    MovieEntity createMovie(MovieEntity movie);

    MovieEntity updateMovie(Integer id, MovieEntity movie);

    void deleteMovie(Integer id);

    List<MovieEntity> getAllMovies();

    MovieEntity getMovieById(Integer id);
}
