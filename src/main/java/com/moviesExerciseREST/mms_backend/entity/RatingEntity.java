package com.moviesExerciseREST.mms_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "rating")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('rating_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_fk", nullable = false)
    private Integer user_fk;

    @Column(name = "movie_fk", nullable = false)
    private Integer movie_fk;

    @Column(name = "rate", nullable = false)
    private Double rate;


    //Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(Integer user_fk) {
        this.user_fk = user_fk;
    }

    public Integer getMovie_fk() {
        return movie_fk;
    }

    public void setMovie_fk(Integer movie_fk) {
        this.movie_fk = movie_fk;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}