package com.moviesExerciseREST.mms_backend.controller;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;
import com.moviesExerciseREST.mms_backend.service.*;
import com.moviesExerciseREST.mms_backend.type.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;

@RestController
//@RequestMapping("/api/movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    //Create controller
    public MovieController(MovieServiceImpl movieService)
    {
        this.movieService = movieService;
    }

    //Define Endpoints
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    //CREATE Operation
    /*------------------*/
    @PostMapping("/api/movies")
    public ResponseEntity<MovieEntity> createMovie(@RequestBody MovieEntity movieEnt) throws MissingFieldException {
        return new ResponseEntity<>(movieService.create(movieEnt), HttpStatus.CREATED);
    }

    //READ Operations
    /*------------------*/
    // Fetch all movies
    @GetMapping("/api/movies")
    public HashMap<String,Object> getMoviesFilteredByDate(@RequestParam(required = false) String date) {
        ResultType<List<MovieEntity>> resultType = new ResultType<>();

        try {
            if (date != null) {
                // Filter movies by date
                List<MovieEntity> movies = movieService.findByDate(date);
                resultType.setResult(movies);
                return resultType.asMap(); // Return movies matching the date
            } else {
                // Return all movies if no date is provided
                List<MovieEntity> movies = movieService.findAll();
                resultType.setResult(movies);
                return resultType.asMap(); // Return all movies found
            }
        }catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }
    }

    // Fetch a specific movie by ID
    @GetMapping("/api/movies/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@PathVariable Long id) {
        Optional<MovieEntity> movie = movieService.findById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Movie found
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Movie not found
    }

    // Fetch movies by title
    @GetMapping(value = "/api/movies", params = "title")
    public ResponseEntity<List<MovieEntity>> getMoviesByTitle(@RequestParam String title) {
        List<MovieEntity> movies = movieService.findByTitle(title);
        return movies.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No movies found
                : new ResponseEntity<>(movies, HttpStatus.OK); // Return matching movies
    }

    //UPDATE Operation
    /*------------------*/
    @PatchMapping("/api/movies/{id}")
    public ResponseEntity<MovieEntity> updateMovie(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        try {
            MovieEntity updatedMovie = movieService.updateMovie(id, updates);
            return ResponseEntity.ok(updatedMovie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Handle movie not found scenario
        }
    }

    //DELETE Operation
    /*------------------*/
    @DeleteMapping("/api/movies/{id}")
    public ResponseEntity<List<MovieEntity>> removeMovie(@PathVariable Long id) {
        try {
            movieService.removeMovie(id);  // Call the service to delete the movie
            List<MovieEntity> movies = movieService.findAll();
            return ResponseEntity.ok(movies);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Return 404 Not Found if movie is not found
        }
    }


}
