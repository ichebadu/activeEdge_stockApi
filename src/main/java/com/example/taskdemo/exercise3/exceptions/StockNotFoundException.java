package com.example.taskdemo.exercise3.exceptions;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String message){
        super(message);

    }
}
