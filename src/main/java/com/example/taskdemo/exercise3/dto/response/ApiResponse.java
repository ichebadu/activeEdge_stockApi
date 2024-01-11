package com.example.taskdemo.exercise3.dto.response;

import com.example.taskdemo.exercise3.enums.ResponseCode;
import com.example.taskdemo.exercise3.exceptions.ExceptionHandlerResponse;
import com.example.taskdemo.exercise3.utils.url.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiResponse<T> {
    private int statusCode;
    private String time;
    private String message;
    private T payLoad;

    public ApiResponse(T payLoad, int statusCode,String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.time = DateUtils.saveDate(LocalDateTime.now());
        this.payLoad = payLoad;
    }
}
