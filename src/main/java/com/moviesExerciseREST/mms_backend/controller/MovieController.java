package com.moviesExerciseREST.mms_backend.controller;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.exception.DuplicatedRecordException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;
import com.moviesExerciseREST.mms_backend.service.*;
import com.moviesExerciseREST.mms_backend.type.ResultType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MovieController {

    private final MovieServiceImpl movieService;

    //Create controller
    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    //Define Endpoints
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    //CREATE Operation
    /*------------------*/
    @PostMapping("/api/movies")
    public HashMap<String,Object> createMovie(@RequestBody MovieEntity movieEnt) throws MissingFieldException, DuplicatedRecordException {
        ResultType<MovieEntity> resultType = new ResultType<>();

        try {
            MovieEntity newMovie = movieService.create(movieEnt);
            resultType.setResult(newMovie);

            return resultType.asMap();
        }catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }
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
    public HashMap<String,Object> getMovieById(@PathVariable Long id) {
        ResultType<MovieEntity> resultType = new ResultType<>();

        try{
            MovieEntity movie = movieService.getMovie(id);
            resultType.setResult(movie);
            return resultType.asMap();

        } catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }

    }

    // Fetch movies by title
    @GetMapping(value = "/api/movies", params = "title")
    public HashMap<String,Object> getMoviesByTitle(@RequestParam String title) {

        ResultType<List<MovieEntity>> resultType = new ResultType<>();

        try {
            List<MovieEntity> movies = movieService.findByTitle(title);
            resultType.setResult(movies);
            return resultType.asMap(); // Return matching movies

        }catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }

    }

    //UPDATE Operation
    /*------------------*/
    @PatchMapping("/api/movies/{id}")
    public HashMap<String,Object> updateMovie(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        ResultType<MovieEntity> resultType = new ResultType<>();
        try {
            MovieEntity updatedMovie = movieService.updateMovie(id, updates);
            resultType.setResult(updatedMovie);
            return resultType.asMap();
        } catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }
    }

    //DELETE Operation
    /*------------------*/
    @DeleteMapping("/api/movies/{id}")
    public void removeMovie(@PathVariable Long id) {
        movieService.removeMovie(id);  // Call the service to delete the movie
    }

}
