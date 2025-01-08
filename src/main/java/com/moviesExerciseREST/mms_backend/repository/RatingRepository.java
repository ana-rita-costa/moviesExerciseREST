package com.moviesExerciseREST.mms_backend.repository;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import com.moviesExerciseREST.mms_backend.entity.RatingEntity;
import com.moviesExerciseREST.mms_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    boolean existsByMovieAndUser(MovieEntity movie, UserEntity user);
    List<RatingEntity> findByMovie(MovieEntity movie);

    Double findAverageRatingByMovieId(Long id);
}
