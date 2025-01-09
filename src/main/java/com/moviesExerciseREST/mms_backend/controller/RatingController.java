package com.moviesExerciseREST.mms_backend.controller;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.entity.RatingEntity;
import com.moviesExerciseREST.mms_backend.exception.DuplicatedRecordException;
import com.moviesExerciseREST.mms_backend.exception.MissingFieldException;
import com.moviesExerciseREST.mms_backend.service.RatingServiceImpl;
import com.moviesExerciseREST.mms_backend.type.ResultType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RatingController {

    private final RatingServiceImpl ratingService;

    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/api/ratings")
    public HashMap<String,Object> createRating(@RequestBody RatingEntity ratingEnt) throws MissingFieldException, DuplicatedRecordException {
        ResultType<RatingEntity> resultType = new ResultType<>();

        try {
            RatingEntity newRating = ratingService.create(ratingEnt);
            resultType.setResult(newRating);

            return resultType.asMap();
        }catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }
    }

    @GetMapping("/api/ratings")
    public HashMap<String,Object> getRatings(@RequestParam(required = false) String date) {
        ResultType<List<RatingEntity>> resultType = new ResultType<>();

        try {
            List<RatingEntity> ratings = ratingService.findAll();
            resultType.setResult(ratings);
            return resultType.asMap(); // Return all users
        }catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }
    }

    @GetMapping(value = "/api/ratings", params = "movie")
    public HashMap<String,Object> getRatingsbyMovie(@RequestParam String movie) {

        ResultType<List<RatingEntity>> resultType = new ResultType<>();

        try {
            List<RatingEntity> ratings = ratingService.getRatingsByMovie(movie);
            resultType.setResult(ratings);
            return resultType.asMap(); // Return matching movies

        }catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }

    }

    @PutMapping("/api/ratings/{id}")
    public HashMap<String,Object> updateRating(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        ResultType<RatingEntity> resultType = new ResultType<>();
        try {
            RatingEntity updatedRating = ratingService.updateRating(id, updates);
            resultType.setResult(updatedRating);
            return resultType.asMap();
        } catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }
    }

}
