package com.example.taskdemo.exercise3.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResponseCode {

    STOCK_ALREADY_EXIST_RESPONSE(-991, "stock already exists"),
    INPUT_NOT_VALID_RESPONSE(-991, "Invalid input data"),
    STOCK_NOT_FOUND_RESPONSE(-991, "stock not found"),
    UPDATE_SUCCESS_RESPONSE(591, "Update successful"),
    STOCK_RESPONSE(-695, "stock created");
//    INPUT_NOT_VALID_RESPONSE(-991, "Invalid input data"),
//    STOCK_NOT_FOUND_RESPONSE(-991, "stock not found"),
//    UPDATE_SUCCESS_RESPONSE(591, "Update successful");
    private final int statusCode;
    private final String message;
}

