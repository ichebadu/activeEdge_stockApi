package com.example.taskdemo.exercise3.exceptions;




public class InputNotValidException extends RuntimeException {
    public InputNotValidException(String message){
        super(message);
    }
}
