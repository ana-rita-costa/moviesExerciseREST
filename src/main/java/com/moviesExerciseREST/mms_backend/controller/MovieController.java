package com.moviesExerciseREST.mms_backend.controller;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.repository.MovieRepository;
import com.moviesExerciseREST.mms_backend.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/movies")
public class MovieController {

    private MovieRepository movieRepository;

    /*MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }*/

    /*@GetMapping("/api/movies")
    List<MovieEntity> all() {
        return movieRepository.findAll();
    }

    @PostMapping("/api/movies")
    MovieEntity newMovie(@RequestBody MovieEntity newMovie) {
        return movieRepository.save(newMovie);
    }*/

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Build Create Movie REST API
   @PostMapping("/api/movies")
    public ResponseEntity<MovieEntity> createMovie(@RequestBody MovieEntity movieEnt){
        return new ResponseEntity<>(movieService.create(movieEnt), HttpStatus.CREATED);
    }
}
