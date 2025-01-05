package com.moviesExerciseREST.mms_backend.exception;

public class MissingFieldException extends Exception {
    public static final String MESSAGE = "The field {FIELD} is missing.";
    private String field;
    public MissingFieldException(String field){
        super(MESSAGE.replace("{FIELD}", field));
        field = field;
    }

}
