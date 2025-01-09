package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.entity.RatingEntity;
import com.moviesExerciseREST.mms_backend.exception.DuplicatedRecordException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;

import java.util.List;
import java.util.Map;

public interface RatingService {

    RatingEntity create(RatingEntity rating) throws MissingFieldException, DuplicatedRecordException;
    List<RatingEntity> findAll();
    List<RatingEntity> findByMovie(MovieEntity movie);
    List<RatingEntity> getRatingsByMovie(String movie);
    RatingEntity updateRating(Long id, Map<String, Object> updates);
    void removeRating(Long id);

}
