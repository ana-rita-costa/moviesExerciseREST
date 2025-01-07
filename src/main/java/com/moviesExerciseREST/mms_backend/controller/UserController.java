package com.moviesExerciseREST.mms_backend.controller;

import com.moviesExerciseREST.mms_backend.entity.UserEntity;
import com.moviesExerciseREST.mms_backend.service.UserServiceImpl;
import com.moviesExerciseREST.mms_backend.type.ResultType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public HashMap<String,Object> getUsers(@RequestParam(required = false) String date) {
        ResultType<List<UserEntity>> resultType = new ResultType<>();

        try {
            List<UserEntity> users = userService.findAll();
            resultType.setResult(users);
            return resultType.asMap(); // Return all users
        }catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }
    }

    // Fetch a specific movie by ID
    @GetMapping("/api/users/{id}")
    public HashMap<String,Object> getMovieById(@PathVariable Long id) {
        ResultType<UserEntity> resultType = new ResultType<>();

        try{
            UserEntity user = userService.getUser(id);
            resultType.setResult(user);
            return resultType.asMap();

        } catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }

    }

    // Fetch movies by title
    @GetMapping(value = "/api/users", params = "username")
    public HashMap<String,Object> getUsersByName(@RequestParam String username) {

        ResultType<List<UserEntity>> resultType = new ResultType<>();

        try {
            List<UserEntity> users = userService.findByUsername(username);
            resultType.setResult(users);
            return resultType.asMap(); // Return matching movies

        }catch(Exception e){
            resultType.setError(e.toString());
            return resultType.asMap();
        }

    }
}
