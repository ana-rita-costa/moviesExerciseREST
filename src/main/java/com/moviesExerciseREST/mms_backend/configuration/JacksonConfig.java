package com.moviesExerciseREST.mms_backend.configuration;

import com.moviesExerciseREST.mms_backend.utils.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
