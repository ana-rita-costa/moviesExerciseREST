package com.moviesExerciseREST.mms_backend.entity;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "rating")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('rating_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_fk", nullable = false, referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "movie_fk", nullable = false, referencedColumnName = "id")
    private MovieEntity movie;

    @Min(0)  // Minimum value is 0
    @Max(10) // Maximum value is 10
    @Column(name = "rate", nullable = false)
    private Double rate;

    @Column(name = "comment")
    private String comment;

    //Getters and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public @Min(0) @Max(10) Double getRate() {
        return rate;
    }

    public void setRate(@Min(0) @Max(10) Double rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonSetter("movie")
    public void setMovieById(Long movieId) {
        this.movie = new MovieEntity();
        this.movie.setId(movieId);
    }

    @JsonSetter("user")
    public void setUserById(Long userId) {
        this.user = new UserEntity();
        this.user.setId(userId);
    }
}