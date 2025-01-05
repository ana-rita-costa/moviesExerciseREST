package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.exception.InvalidValuesException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieService {

    MovieEntity create(MovieEntity movie) throws MissingFieldException;

    List<MovieEntity> findAll();

    // Method to get a movie by its ID
    Optional<MovieEntity> findById(Long id);

    // Method to find movies by title
    List<MovieEntity> findByTitle(String title);

    List<MovieEntity> findByDate(String date) throws InvalidValuesException;

    MovieEntity updateMovie(Long id, Map<String, Object> updates);

    void removeMovie(Long id);

}
