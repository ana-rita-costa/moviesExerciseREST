package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.exception.DuplicatedRecordException;
import com.moviesExerciseREST.mms_backend.exception.InvalidValuesException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;
import com.moviesExerciseREST.mms_backend.repository.MovieRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.moviesExerciseREST.mms_backend.utils.Utils.isValidDate;
import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //Implement CREATE method
    @Override
    public MovieEntity create(MovieEntity movie) throws MissingFieldException, DuplicatedRecordException{
        if(movie.getTitle() == null ) throw new MissingFieldException("title");
        if(movie.getDate() == null ) throw new MissingFieldException("date");
        if(this.movieRepository.existsByTitleAndDate(movie.getTitle(),movie.getDate())) throw new DuplicatedRecordException("Movie");

        if (movie.getPoster() != null && movie.getPoster().length > 0) {
            String base64String = new String(movie.getPoster());
            movie.setPoster(decodeBase64(base64String));  // Decoding the Base64 string to byte[]
        }

        return movieRepository.save(movie);
    }

    //Implement READ methods
    @Override
    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<MovieEntity> findAll(String sortBy) {
        Sort sort = "desc".equals(sortBy) ? Sort.by(Sort.Order.desc("rank")) : Sort.by(Sort.Order.asc("rank"));
        return movieRepository.findAll(sort);
    }

    @Override
    public MovieEntity getMovie(Long id){
        return this.movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<MovieEntity> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<MovieEntity> findByDate(String date) throws InvalidValuesException{

        if(!isValidDate(date)) throw new InvalidValuesException("date");
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
                    case "poster":
                        // Check if the poster is a base64 string and decode it
                        if (value instanceof String) {
                            String base64String = (String) value;
                            movie.setPoster(decodeBase64(base64String));  // Decoding the base64 string to byte[]
                        }
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

        movieRepository.deleteById(id);
        if (movieRepository.existsById(id)) {  // Optional: Check if the movie exists first
            movieRepository.deleteById(id);    // This should work now
        } else {
            throw new RuntimeException("Movie not found");
        }
    }

}
