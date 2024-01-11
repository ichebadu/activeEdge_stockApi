package com.example.taskdemo.exercise3.controller;

import com.example.taskdemo.exercise3.dto.request.CreateStockRequest;
import com.example.taskdemo.exercise3.dto.request.UpdateStockRequest;
import com.example.taskdemo.exercise3.dto.response.ApiResponse;
import com.example.taskdemo.exercise3.dto.response.StockResponse;
import com.example.taskdemo.exercise3.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.taskdemo.exercise3.utils.url.stockUrl.*;

@RestController
@RequestMapping(STOCK_BASE_URL)
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping(CREATE_STOCK)
    public ResponseEntity<ApiResponse<StockResponse>> create(@RequestBody CreateStockRequest createStockrequest) {
        return new ResponseEntity<>(stockService.create(createStockrequest),HttpStatus.CREATED);
    }
    @PutMapping(UPDATE_STOCK)
    public ResponseEntity<ApiResponse<StockResponse>> update(@RequestBody UpdateStockRequest updateStockRequest) {
        return new ResponseEntity<>(stockService.update(updateStockRequest),HttpStatus.OK);
    }
    @GetMapping(GET_STOCK)
    public ResponseEntity<ApiResponse<StockResponse>> getSingleStock(@PathVariable("id") Long id ) {
        return new ResponseEntity<>(stockService.getStock(id),HttpStatus.OK);
    }
    @GetMapping(LIST_OF_STOCK)
    public ResponseEntity<ApiResponse<List<StockResponse>>> getListOfStock() {
        return new ResponseEntity<>(stockService.getListOfStock(),HttpStatus.OK);
    }
}
