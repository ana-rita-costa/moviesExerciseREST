package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface GetMovieService {

    List<MovieEntity> findAll();

    // Method to get a movie by its ID
    Optional<MovieEntity> findById(Long id);

    // Method to find movies by title
    List<MovieEntity> findByTitle(String title);
}
