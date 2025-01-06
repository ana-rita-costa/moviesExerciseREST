package com.moviesExerciseREST.mms_backend.repository;

import com.moviesExerciseREST.mms_backend.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findByTitle(String title);
    List<MovieEntity> findByDate(LocalDate date);
    boolean existsByTitleAndDate(String title, LocalDate date);


}
