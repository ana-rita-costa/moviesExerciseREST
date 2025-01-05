package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;

import java.util.Map;

public interface UpdateMovieService {
    MovieEntity updateMovie(Long id, Map<String, Object> updates);
}
