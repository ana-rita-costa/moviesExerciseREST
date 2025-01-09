package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.entity.RatingEntity;
import com.moviesExerciseREST.mms_backend.exception.DuplicatedRecordException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;
import com.moviesExerciseREST.mms_backend.repository.MovieRepository;
import com.moviesExerciseREST.mms_backend.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;

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
        if (rating.getUser() == null) {
            throw new MissingFieldException("user");
        }
        if (rating.getMovie() == null) {
            throw new MissingFieldException("movie");
        }
        if (this.ratingRepository.existsByMovieAndUser(rating.getMovie(), rating.getUser())) {
            throw new DuplicatedRecordException("Rating");
        }

        // Save the rating first
        RatingEntity savedRating = ratingRepository.save(rating);

        // Fetch the complete MovieEntity from the repository
        MovieEntity movie = movieRepository.findById(rating.getMovie().getId())
                .orElseThrow(() -> new IllegalStateException("Movie not found"));

        // Retrieve all ratings for the movie
        List<RatingEntity> movieRatings = ratingRepository.findByMovie(movie);

        // Calculate the average rating for the movie
        Double averageRating = movieRatings.stream()
                .mapToDouble(RatingEntity::getRate)  // Extract the rate
                .average()                          // Calculate average
                .orElse(0.0);                       // Default to 0 if no ratings

        // Update the rank of the movie
        movie.setRank(averageRating);  // Set the computed rank

        // Persist the updated movie entity
        movieRepository.saveAndFlush(movie);  // Ensure immediate persistence

        return savedRating;
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

    @Override
    public RatingEntity updateRating(Long id, Map<String, Object> updates) {

        Optional<RatingEntity> optionalRating = ratingRepository.findById(id);

        if (optionalRating.isPresent()) {
            RatingEntity rating = optionalRating.get();

            // Apply updates (using a utility or manually)
            updates.forEach((key, value) -> {
                switch (key) {
                    case "movie":
                        if (value instanceof Number)  rating.setMovieById(((Number) value).longValue());
                        break;
                    case "user":
                        if (value instanceof Number)  rating.setUserById(((Number) value).longValue());
                        break;
                    case "rate":
                        rating.setRate(Double.valueOf(value.toString()));
                        break;
                    case "comment":
                        rating.setComment((String) value);
                        break;
                }
            });

            // Save updated movie
            return ratingRepository.save(rating);
        } else {
            throw new RuntimeException("Rating not found");
        }
    }

    @Override
    public void removeRating(Long id) {
        if (ratingRepository.existsById(id)) {  // Optional: Check if the movie exists first
            ratingRepository.deleteById(id);    // This should work now
        } else {
            throw new RuntimeException("Rating not found");
        }
    }

}
