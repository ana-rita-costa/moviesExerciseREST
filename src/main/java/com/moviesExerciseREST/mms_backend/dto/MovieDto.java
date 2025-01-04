package com.moviesExerciseREST.mms_backend.dto;

import com.moviesExerciseREST.mms_backend.entity.Rank;
import com.moviesExerciseREST.mms_backend.entity.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;
    private Title title;
    private Date date;
    private Rank rank;
    private BigDecimal revenue;
}
