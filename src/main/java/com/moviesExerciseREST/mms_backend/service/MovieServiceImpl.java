package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.exception.InvalidValuesException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;
import com.moviesExerciseREST.mms_backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.moviesExerciseREST.mms_backend.utils.Utils.isValidDate;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //Implement CREATE method
    @Override
    public MovieEntity create(MovieEntity movie) throws MissingFieldException{
        if(movie.getTitle() == null ) throw new MissingFieldException("title");
        if(movie.getDate() == null ) throw new MissingFieldException("date");

        return movieRepository.save(movie);
    }

    //Implement READ methods
    @Override
    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<MovieEntity> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<MovieEntity> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<MovieEntity> findByDate(String date) throws InvalidValuesException{

        if(isValidDate(date)) throw new InvalidValuesException("date");
        LocalDate d;
        d=LocalDate.parse(date);
        return movieRepository.findByDate(d);
    }

    //Implement UPDATE method
    @Override
    public MovieEntity updateMovie(Long id, Map<String, Object> updates) {
        Optional<MovieEntity> movieOptional = movieRepository.findById(id);

        if (movieOptional.isPresent()) {
            MovieEntity movie = movieOptional.get();

            // Apply updates (using a utility or manually)
            updates.forEach((key, value) -> {
                switch (key) {
                    case "title":
                        movie.setTitle((String) value);
                        break;
                    case "date":
                        movie.setDate(LocalDate.parse((String) value));
                        break;
                    case "rank":
                        movie.setRank(Double.valueOf(value.toString()));
                        break;
                    case "revenue":
                        movie.setRevenue(Double.valueOf(value.toString()));
                        break;
                }
            });

            // Save updated movie
            return movieRepository.save(movie);
        } else {
            throw new RuntimeException("Movie not found");
        }
    }

    //Implement DELETE method
    @Override
    public void removeMovie(Long id) {
        if (movieRepository.existsById(id)) {  // Optional: Check if the movie exists first
            movieRepository.deleteById(id);    // This should work now
        } else {
            throw new RuntimeException("Movie not found");
        }
    }

}
