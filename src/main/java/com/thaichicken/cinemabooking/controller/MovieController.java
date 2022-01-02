package com.thaichicken.cinemabooking.controller;

import com.thaichicken.cinemabooking.dto.MovieDTO;
import com.thaichicken.cinemabooking.model.MovieEntity;
import com.thaichicken.cinemabooking.service.DefaultMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final ModelMapper modelMapper;

    private final DefaultMovieService movieService;

    public MovieController(DefaultMovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> movieEntities = movieService.getAllMovies();
        return movieEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie/{id}")
    @ResponseBody
    public MovieDTO getMovie(@PathVariable(value = "id") Integer id) {
        return convertToDto(movieService.getMovieById(id));
    }

    @PostMapping("/movie")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MovieDTO createMovie(@RequestBody MovieDTO movieDto) {
        MovieEntity movie = convertToEntity(movieDto);
        MovieEntity movieCreated = movieService.createMovie(movie);
        return convertToDto(movieCreated);
    }

    @PutMapping("/movie/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MovieDTO updateMovie(@PathVariable(value = "id") Integer id,
                                @RequestBody MovieDTO movieDto) {
        if (!Objects.equals(id, movieDto.getMovieId())) {
            throw new IllegalArgumentException("IDS don't match");
        }
        MovieEntity movie = convertToEntity(movieDto);
        MovieEntity movieUpdated = movieService.updateMovie(id, movie);
        return convertToDto(movieUpdated);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable(value = "id") Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    private MovieDTO convertToDto(MovieEntity movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }

    private MovieEntity convertToEntity(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, MovieEntity.class);
    }
}
