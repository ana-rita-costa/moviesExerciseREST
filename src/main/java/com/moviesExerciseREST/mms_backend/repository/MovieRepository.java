package com.moviesExerciseREST.mms_backend.repository;

import com.moviesExerciseREST.mms_backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
