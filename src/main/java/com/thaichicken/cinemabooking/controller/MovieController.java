package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.model.MovieEntity;
import com.thaichicken.cinemabooking.service.DefaultMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final DefaultMovieService movieService;

    public MovieController(DefaultMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieEntity> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public MovieEntity getMovie(@PathVariable(value = "id") Integer id) {
        return movieService.getMovieById(id);
    }

    @PostMapping("/movie")
    public MovieEntity createMovie(@RequestBody MovieEntity movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/movie/{id}")
    public MovieEntity updateMovie(@PathVariable(value = "id") Integer id,
                                   @RequestBody MovieEntity movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable(value = "id") Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
