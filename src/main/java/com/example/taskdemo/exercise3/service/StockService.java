package com.example.taskdemo.exercise3.service;

import com.example.taskdemo.exercise3.dto.request.CreateStockRequest;
import com.example.taskdemo.exercise3.dto.request.UpdateStockRequest;
import com.example.taskdemo.exercise3.dto.response.ApiResponse;
import com.example.taskdemo.exercise3.dto.response.StockResponse;

import java.util.List;

public interface StockService {
    ApiResponse<StockResponse> create(CreateStockRequest createStockrequest);

    ApiResponse<StockResponse> update(UpdateStockRequest updateStockRequest);
    ApiResponse<StockResponse> getStock(Long id);
    ApiResponse<List<StockResponse>> getListOfStock();

}
