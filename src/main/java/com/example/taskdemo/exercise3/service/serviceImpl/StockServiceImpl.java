package com.example.taskdemo.exercise3.service.serviceImpl;

import com.example.taskdemo.exercise3.dto.request.CreateStockRequest;
import com.example.taskdemo.exercise3.dto.request.UpdateStockRequest;
import com.example.taskdemo.exercise3.dto.response.ApiResponse;
import com.example.taskdemo.exercise3.dto.response.StockResponse;
import com.example.taskdemo.exercise3.enums.ResponseCode;
import com.example.taskdemo.exercise3.exceptions.StockNotFoundException;
import com.example.taskdemo.exercise3.model.Stock;
import com.example.taskdemo.exercise3.repository.StockRepository;
import com.example.taskdemo.exercise3.service.StockService;
import com.example.taskdemo.exercise3.utils.Validations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final Validations<CreateStockRequest> createStockRequestValidations;
    private final Validations<UpdateStockRequest> updateValidations;


    @Override
    public ApiResponse<StockResponse> create(CreateStockRequest createStockrequest) {
        createStockRequestValidations.validate(createStockrequest);
        Stock stock = Stock.builder()
                .name(createStockrequest.getName())
                .currentPrice(createStockrequest.getCurrentPrice())
                .build();
        stockRepository.save(stock);
        StockResponse stockResponse = StockResponse.builder()
                .id(stock.getId())
                .name(stock.getName())
                .currentPrice(stock.getCurrentPrice())
                .createDate(stock.getCreateDate())
                .lastUpdate(stock.getLastUpdate())
                .build();
        return new ApiResponse<>(stockResponse, ResponseCode.STOCK_RESPONSE.getStatusCode(), ResponseCode.STOCK_RESPONSE.getMessage());
    }

    @Override
    public ApiResponse<StockResponse> update(UpdateStockRequest updateStockRequest) {
        updateValidations.validate(updateStockRequest);

        verifyStock(updateStockRequest.getId());
        Stock stock = verifyStock(updateStockRequest.getId());
        stock.setName(updateStockRequest.getName());
        stock.setCurrentPrice(updateStockRequest.getCurrentPrice());
        stockRepository.save(stock);

        StockResponse stockResponse = StockResponse.builder()
                .id(stock.getId())
                .name(stock.getName())
                .currentPrice(stock.getCurrentPrice())
                .createDate(stock.getCreateDate())
                .lastUpdate(stock.getLastUpdate())
                .build();

        return new ApiResponse<>(stockResponse, ResponseCode.UPDATE_SUCCESS_RESPONSE.getStatusCode(), ResponseCode.UPDATE_SUCCESS_RESPONSE.getMessage());

    }

    @Override
    public ApiResponse<StockResponse> getStock(Long id) {
        Stock stock = verifyStock(id);
        StockResponse stockResponse = StockResponse.builder()
                .id(stock.getId())
                .name(stock.getName())
                .currentPrice(stock.getCurrentPrice())
                .createDate(stock.getCreateDate())
                .lastUpdate(stock.getLastUpdate())
                .build();
        ApiResponse<StockResponse> apiResponse = new ApiResponse<>(stockResponse, ResponseCode.STOCK_RESPONSE.getStatusCode(), ResponseCode.UPDATE_SUCCESS_RESPONSE.getMessage());
        return apiResponse;
    }

    @Override
    public ApiResponse<List<StockResponse>> getListOfStock() {
        List<StockResponse> stockResponses = stockRepository.findAll()
                .stream().map(stock -> StockResponse.builder()
                        .name(stock.getName())
                        .currentPrice(stock.getCurrentPrice())
                        .createDate(stock.getCreateDate())
                        .lastUpdate(stock.getLastUpdate())
                        .build())
                .collect(Collectors.toList());

        return new ApiResponse<>( stockResponses,ResponseCode.STOCK_RESPONSE.getStatusCode(), ResponseCode.UPDATE_SUCCESS_RESPONSE.getMessage());
    }

    private Stock verifyStock(Long stockId) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(
                () -> new StockNotFoundException("stock not found"));
        return stock;

    }
}