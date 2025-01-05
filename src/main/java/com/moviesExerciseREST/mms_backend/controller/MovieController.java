package com.moviesExerciseREST.mms_backend.controller;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.service.CreateMovieService;
import com.moviesExerciseREST.mms_backend.service.GetMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/movies")
public class MovieController {

    //Declare services
    private CreateMovieService createMovieService;
    private GetMovieService getMovieService;

    //Create controller
    public MovieController(CreateMovieService createMovieService, GetMovieService getMovieService) {
        this.createMovieService = createMovieService;
        this.getMovieService = getMovieService;
    }

    //Define Endpoints
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    //Create Operation
    @PostMapping("/api/movies")
    public ResponseEntity<MovieEntity> createMovie(@RequestBody MovieEntity movieEnt){
        return new ResponseEntity<>(createMovieService.create(movieEnt), HttpStatus.CREATED);
    }
    //Read Operation
    @GetMapping("/api/movies")
    public ResponseEntity<?> getMovies(@RequestParam(required = false) Long id, @RequestParam(required = false) String title) {
        if (id != null) {
            // Fetch movie by id
            Optional<MovieEntity> movie = getMovieService.findById(id);
            return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Movie found
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Movie not found
        } else if (title != null) {
            // Fetch movies by title
            List<MovieEntity> movies = getMovieService.findByTitle(title);
            return movies.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No content if no movies match the title
                    : new ResponseEntity<>(movies, HttpStatus.OK); // Return matching movies with 200 OK
        } else {
            // Fetch all movies
            List<MovieEntity> movies = getMovieService.findAll();
            return movies.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No content if no movies are found
                    : new ResponseEntity<>(movies, HttpStatus.OK); // Return movies with 200 OK
        }
    }


    /*@GetMapping("/api/movies")
    public ResponseEntity<MovieEntity> getMovie(@RequestBody MovieEntity movieEnt){
        return new ResponseEntity<>(getMovieService.create(movieEnt), HttpStatus.CREATED);
    }*/

    /*@GetMapping("/api/searchMovies")
    public ResponseEntity<List<MovieEntity>> getMoviesByTitle(@RequestParam String title) {
        List<MovieEntity> movies = getMovieService.findByTitle(title);
        return movies.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(movies, HttpStatus.OK);
    }*/

    /*WORKING ONE
    @GetMapping("/api/movies")
    public ResponseEntity<List<MovieEntity>> getAllMovies() {
        List<MovieEntity> movies = getMovieService.findAll();
        return movies.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No content if no movies are found
                : new ResponseEntity<>(movies, HttpStatus.OK); // Return movies with 200 OK
    }*/

    // 2. Endpoint to get a movie by its ID
    /*@GetMapping("/api/movies/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@PathVariable Long id) {
        Optional<MovieEntity> movie = getMovieService.findById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Movie found
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Movie not found
    }*/

    /*@GetMapping("/api/teste")
    public ResponseEntity<MovieEntity> getMovieById(@RequestParam Long id) {
        Optional<MovieEntity> movie = getMovieService.findById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Movie found
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Movie not found
    }*/

    /*WORKING

    @GetMapping("/api/movies")
    public ResponseEntity<?> getMovies(@RequestParam(required = false) Long id, @RequestParam(required = false) String title) {
        if (id != null) {
            // Fetch movie by id
            Optional<MovieEntity> movie = getMovieService.findById(id);
            return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Movie found
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Movie not found
        } else if (title != null) {
            // Fetch movies by title
            List<MovieEntity> movies = getMovieService.findByTitle(title);
            return movies.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No content if no movies match the title
                    : new ResponseEntity<>(movies, HttpStatus.OK); // Return matching movies with 200 OK
        } else {
            // Fetch all movies
            List<MovieEntity> movies = getMovieService.findAll();
            return movies.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No content if no movies are found
                    : new ResponseEntity<>(movies, HttpStatus.OK); // Return movies with 200 OK
        }
    }*/

    // 3. Endpoint to find movies by title
    /*@GetMapping("/api/movies/{title}")
    public ResponseEntity<List<MovieEntity>> getMoviesByTitle(@PathVariable String title) {
        List<MovieEntity> movies = getMovieService.findByTitle(title);
        return movies.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // No content if no movies match the title
                : new ResponseEntity<>(movies, HttpStatus.OK); // Return matching movies with 200 OK
    }*/




}
