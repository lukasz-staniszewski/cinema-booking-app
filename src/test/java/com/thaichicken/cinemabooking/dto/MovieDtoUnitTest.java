package com.thaichicken.cinemabooking.dto;

import com.thaichicken.cinemabooking.model.MovieEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class MovieDtoUnitTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertMovieEntityToMovieDto_thenCorrect() {
        MovieEntity movie = new MovieEntity();
        movie.setMovieId(1);
        movie.setDirector("Test");
        movie.setType("Test");
        movie.setDescription("Test");
        movie.setLength(123);
        movie.setName("Test");

        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        Assertions.assertEquals(movieDTO.getMovieId(), movie.getMovieId());
        Assertions.assertEquals(movieDTO.getDirector(), movie.getDirector());
        Assertions.assertEquals(movieDTO.getType(), movie.getType());
        Assertions.assertEquals(movieDTO.getDescription(), movie.getDescription());
        Assertions.assertEquals(movieDTO.getLength(), movie.getLength());
        Assertions.assertEquals(movieDTO.getName(), movie.getName());
    }

    @Test
    public void whenConvertMovieDtoToMovieEntity_thenCorrect() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(1);
        movieDTO.setDirector("Test");
        movieDTO.setType("Test");
        movieDTO.setDescription("Test");
        movieDTO.setLength(123);
        movieDTO.setName("Test");

        MovieEntity movie = modelMapper.map(movieDTO, MovieEntity.class);
        Assertions.assertEquals(movieDTO.getMovieId(), movie.getMovieId());
        Assertions.assertEquals(movieDTO.getDirector(), movie.getDirector());
        Assertions.assertEquals(movieDTO.getType(), movie.getType());
        Assertions.assertEquals(movieDTO.getDescription(), movie.getDescription());
        Assertions.assertEquals(movieDTO.getLength(), movie.getLength());
        Assertions.assertEquals(movieDTO.getName(), movie.getName());
    }
}
