package com.moviesExerciseREST.mms_backend.service;

import com.moviesExerciseREST.mms_backend.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();
    List<UserEntity> findByUsername(String username);
    UserEntity getUser(Long id);
}
