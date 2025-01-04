package com.moviesExerciseREST.mms_backend.mapper;

import com.moviesExerciseREST.mms_backend.dto.MovieDto;
import com.moviesExerciseREST.mms_backend.entity.Movie;

public class MovieMapper {

    public static MovieDto mapToMovieDto(Movie movie){
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDate(),
                movie.getRank(),
                movie.getRevenue()
        );
    }

    public static Movie mapToMovie(MovieDto movieDto){
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getDate(),
                movieDto.getRank(),
                movieDto.getRevenue()
        );
    }
}
