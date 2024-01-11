package com.example.taskdemo.exercise3.exceptions;

public class StockAlreadyExistException extends RuntimeException{

    public StockAlreadyExistException(String message){
        super(message);
    }
}
