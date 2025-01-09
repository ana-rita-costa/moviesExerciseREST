package com.moviesExerciseREST.mms_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Blob;
import java.time.LocalDate;


@Entity
@Table(name = "movie")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('movie_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "rank")
    private Double rank;

    @Column(name = "revenue", nullable = false)
    private Double revenue;

    @Column(name = "poster")
    //private Blob poster;
    private byte[] poster;


    /*public MovieEntity(Long id, String title, LocalDate date, Double rank, Double revenue) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.rank = rank;
        this.revenue = revenue;
        //this.poster = poster;
    }

    MovieEntity(){}*/

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

   public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }

}
