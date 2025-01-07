package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.exception.DuplicatedRecordException;
import com.moviesExerciseREST.mms_backend.exception.InvalidValuesException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;

import java.util.List;
import java.util.Map;

public interface MovieService {

    MovieEntity create(MovieEntity movie) throws MissingFieldException, DuplicatedRecordException;

    List<MovieEntity> findAll();

    // Method to find movies by title
    List<MovieEntity> findByTitle(String title);

    List<MovieEntity> findByDate(String date) throws InvalidValuesException;

    MovieEntity updateMovie(Long id, Map<String, Object> updates);

    void removeMovie(Long id);

    MovieEntity getMovie(Long id);

}
