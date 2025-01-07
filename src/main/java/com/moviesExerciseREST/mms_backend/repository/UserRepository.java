package com.moviesExerciseREST.mms_backend.repository;

import com.moviesExerciseREST.mms_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUsername(String username);
}
