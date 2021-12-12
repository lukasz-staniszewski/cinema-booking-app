package com.thaichicken.cinemabooking.service;

import com.thaichicken.cinemabooking.exception.ResourceNotFoundException;
import com.thaichicken.cinemabooking.model.MovieEntity;
import com.thaichicken.cinemabooking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieEntity createMovie(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    @Override
    public MovieEntity updateMovie(Integer id, MovieEntity movie) {
        return movieRepository.findById(id)
                .map(movie1 -> {
                    movie1.setName(movie.getName());
                    movie1.setDescription(movie.getDescription());
                    movie1.setLength(movie.getLength());
                    movie1.setProductionYear(movie.getProductionYear());
                    movie1.setType(movie.getType());
                    movie1.setDirector(movie.getDirector());
                    return movieRepository.save(movie1);
                }).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));
    }

    @Override
    public void deleteMovie(Integer id) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));
        movieRepository.delete(movie);
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public MovieEntity getMovieById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));
    }
}
