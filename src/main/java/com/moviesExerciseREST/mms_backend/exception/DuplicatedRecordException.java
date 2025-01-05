package com.moviesExerciseREST.mms_backend.exception;

public class DuplicatedRecordException extends Exception {
    public static final String MESSAGE = "The record {ENTITY} already exists.";
    private String identifier;
    public DuplicatedRecordException(String entity){
        super(MESSAGE.replace("{ENTITY}", entity));
        identifier = entity;
    }

    public String getIdentifier() {
        return identifier;
    }

    public DuplicatedRecordException setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }
}
