package com.moviesExerciseREST.mms_backend.exception;

public class MovieNotFoundException extends RuntimeException{

    MovieNotFoundException(Long id) {
        super("Could not find Movie " + id);
    }
}
