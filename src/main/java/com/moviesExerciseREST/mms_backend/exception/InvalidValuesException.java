package com.moviesExerciseREST.mms_backend.exception;

public class InvalidValuesException extends Exception {
    public static final String MESSAGE = "Invalid value was provided: {FIELD}.";
    private String field;
    public InvalidValuesException(String field){
        super(MESSAGE.replace("{FIELD}", field));
        field = field;
    }

}