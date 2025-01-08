package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.entity.RatingEntity;
import com.moviesExerciseREST.mms_backend.exception.DuplicatedRecordException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;
import com.moviesExerciseREST.mms_backend.repository.MovieRepository;
import com.moviesExerciseREST.mms_backend.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;


    public RatingServiceImpl(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public RatingEntity create(RatingEntity rating) throws MissingFieldException, DuplicatedRecordException {
        if(rating.getUser() == null ) throw new MissingFieldException("user");
        if(rating.getMovie() == null ) throw new MissingFieldException("movie");
        if(this.ratingRepository.existsByMovieAndUser(rating.getMovie(), rating.getUser())) throw new DuplicatedRecordException("Rating");

        return ratingRepository.save(rating);
    }

    @Override
    public List<RatingEntity> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<RatingEntity> findByMovie(MovieEntity movie) {
        return ratingRepository.findByMovie(movie);
    }

    @Override
    public List<RatingEntity> getRatingsByMovie(String movie) {
        // Find movies by title (assuming it returns a list of MovieEntity objects)
        List<MovieEntity> movies = movieRepository.findByTitle(movie);

        if (movies.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if no movies are found
        }

        // Initialize a list to store all ratings
        List<RatingEntity> allRatings = new ArrayList<>();

        // Loop through each movie and get the ratings
        for (MovieEntity m : movies) {
            // Get the ratings for the current movie and add them to the list
            List<RatingEntity> ratings = ratingRepository.findByMovie(m);
            allRatings.addAll(ratings);
        }

        return allRatings; // Return the list of ratings for the movies
    }


}
