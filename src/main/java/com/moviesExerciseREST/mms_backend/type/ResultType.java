package com.moviesExerciseREST.mms_backend.type;

import java.util.Date;
import java.util.HashMap;

public class ResultType<T>{

    private Date time = new Date();
    private T result;
    private String error;
    private final HashMap<String, Object> other = new HashMap<>();

    public ResultType(){

    }

    public ResultType(T result, String error) {
        this.result = result;
        this.error = error;
    }

    public boolean isValid(){
        return error == null;
    }

    public ResultType<T> addOther(String key, Object value){
        other.put(key, value);
        return this;
    }

    public ResultType<T> setError(String error){
        this.error = error;
        return this;
    }

    public static <T> ResultType<T> sucess(T result){
        return new ResultType<>(result,null);
    }

    public static <T> ResultType<T> sucess(){
        return new ResultType<>(null,null);
    }

    public static <T> ResultType<T> failure(String message){
        return new ResultType<>(null, message);
    }

    public static <T> ResultType<T> failure(){
        return new ResultType<>(null, "An error occured processing this request.");
    }

    public void setResult(T result){
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public HashMap<String, Object> asMap(){
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("result", result);
        ret.put("error", error);
        ret.put("valid", this.isValid());
        ret.put("time", this.time);
        for(String key : this.other.keySet()) if (!ret.containsKey(key)) ret.put(key, this.other.get(key));
        return ret;
    }

}
