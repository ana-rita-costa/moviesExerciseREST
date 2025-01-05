package com.moviesExerciseREST.mms_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "ratingUser")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('user_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;


    //Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
