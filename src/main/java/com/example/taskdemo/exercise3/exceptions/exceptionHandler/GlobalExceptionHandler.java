package com.example.taskdemo.exercise3.exceptions.exceptionHandler;

import com.example.taskdemo.exercise3.dto.response.ApiResponse;
import com.example.taskdemo.exercise3.dto.response.StockResponse;
import com.example.taskdemo.exercise3.enums.ResponseCode;
import com.example.taskdemo.exercise3.exceptions.ExceptionHandlerResponse;
import com.example.taskdemo.exercise3.exceptions.InputNotValidException;
import com.example.taskdemo.exercise3.exceptions.StockAlreadyExistException;
import com.example.taskdemo.exercise3.exceptions.StockNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ApiResponse<ExceptionHandlerResponse>> stockNotFoundException(StockNotFoundException e, HttpServletRequest request){
        ExceptionHandlerResponse exceptionHandlerResponse= ExceptionHandlerResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        ApiResponse<ExceptionHandlerResponse> apiResponse = new ApiResponse<>(exceptionHandlerResponse,ResponseCode.STOCK_NOT_FOUND_RESPONSE.getStatusCode(), ResponseCode.STOCK_NOT_FOUND_RESPONSE.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InputNotValidException.class)
    public ResponseEntity<ApiResponse<ExceptionHandlerResponse>> inputNotValidException(InputNotValidException e,HttpServletRequest request){
        ExceptionHandlerResponse exceptionHandlerResponse = ExceptionHandlerResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        ApiResponse<ExceptionHandlerResponse> apiResponse = new ApiResponse<>(exceptionHandlerResponse,ResponseCode.INPUT_NOT_VALID_RESPONSE.getStatusCode(), ResponseCode.INPUT_NOT_VALID_RESPONSE.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StockAlreadyExistException.class)
    public ResponseEntity<ApiResponse<ExceptionHandlerResponse>> stockAlreadyExistException(StockAlreadyExistException e, HttpServletRequest request){
        ExceptionHandlerResponse exceptionHandlerResponse = ExceptionHandlerResponse.builder()
                .path(request.getRequestURI())
                .message(e.getMessage())
                .build();
        ApiResponse<ExceptionHandlerResponse> apiResponseVo = new ApiResponse<>
                (exceptionHandlerResponse, ResponseCode.STOCK_ALREADY_EXIST_RESPONSE.getStatusCode(), ResponseCode.STOCK_ALREADY_EXIST_RESPONSE.getMessage());
        return new ResponseEntity<>(apiResponseVo, HttpStatus.CONFLICT);
    }
}
