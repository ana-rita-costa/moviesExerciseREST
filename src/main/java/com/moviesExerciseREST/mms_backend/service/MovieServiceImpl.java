package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieEntity create(MovieEntity movie){
        return movieRepository.save(movie);
    }


}
