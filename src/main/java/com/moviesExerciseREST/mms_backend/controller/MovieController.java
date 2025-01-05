package com.moviesExerciseREST.mms_backend.controller;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.service.CreateMovieService;
import com.moviesExerciseREST.mms_backend.service.DeleteMovieService;
import com.moviesExerciseREST.mms_backend.service.GetMovieService;
import com.moviesExerciseREST.mms_backend.service.UpdateMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;

@RestController
//@RequestMapping("/api/movies")
public class MovieController {

    //Declare services
    private final CreateMovieService createMovieService;
    private final GetMovieService getMovieService;
    private final UpdateMovieService updateMovieService;
    private final DeleteMovieService deleteMovieService;



    //Create controller
    public MovieController(CreateMovieService createMovieService,
                           GetMovieService getMovieService,
                           UpdateMovieService updateMovieService,
                           DeleteMovieService deleteMovieService)
    {
        this.createMovieService = createMovieService;
        this.getMovieService = getMovieService;
        this.updateMovieService = updateMovieService;
        this.deleteMovieService = deleteMovieService;

    }

    //Define Endpoints
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    //CREATE Operation
    /*------------------*/
    @PostMapping("/api/movies")
    public ResponseEntity<MovieEntity> createMovie(@RequestBody MovieEntity movieEnt){
        return new ResponseEntity<>(createMovieService.create(movieEnt), HttpStatus.CREATED);
    }

    //READ Operations
    /*------------------*/
    // Fetch all movies
    @GetMapping("/api/movies")
    public ResponseEntity<List<MovieEntity>> getMoviesFilteredByDate(@RequestParam(required = false) LocalDate date) {
        if (date != null) {
            // Filter movies by date
            List<MovieEntity> movies = getMovieService.findByDate(date);
            return movies.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No movies match the date
                    : new ResponseEntity<>(movies, HttpStatus.OK); // Return movies matching the date
        } else {
            // Return all movies if no date is provided
            List<MovieEntity> movies = getMovieService.findAll();
            return movies.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No movies found
                    : new ResponseEntity<>(movies, HttpStatus.OK); // Return all movies
        }
    }

    // Fetch a specific movie by ID
    @GetMapping("/api/movies/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@PathVariable Long id) {
        Optional<MovieEntity> movie = getMovieService.findById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Movie found
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Movie not found
    }

    // Fetch movies by title
    @GetMapping(value = "/api/movies", params = "title")
    public ResponseEntity<List<MovieEntity>> getMoviesByTitle(@RequestParam String title) {
        List<MovieEntity> movies = getMovieService.findByTitle(title);
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
            MovieEntity updatedMovie = updateMovieService.updateMovie(id, updates);
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
            deleteMovieService.removeMovie(id);  // Call the service to delete the movie
            List<MovieEntity> movies = getMovieService.findAll();
            return ResponseEntity.ok(movies);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Return 404 Not Found if movie is not found
        }
    }


}
