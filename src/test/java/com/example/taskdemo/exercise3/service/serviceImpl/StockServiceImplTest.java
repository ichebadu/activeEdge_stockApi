package com.example.taskdemo.exercise3.service.serviceImpl;

import com.example.taskdemo.exercise3.dto.request.CreateStockRequest;
import com.example.taskdemo.exercise3.dto.request.UpdateStockRequest;
import com.example.taskdemo.exercise3.dto.response.ApiResponse;
import com.example.taskdemo.exercise3.dto.response.StockResponse;
import com.example.taskdemo.exercise3.enums.ResponseCode;
import com.example.taskdemo.exercise3.model.Stock;
import com.example.taskdemo.exercise3.repository.StockRepository;
import com.example.taskdemo.exercise3.utils.Validations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockServiceImplTest {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;
    @Mock
    private Validations<CreateStockRequest> createStockRequestValidations;

    @Mock
    private Validations<UpdateStockRequest> updateValidations;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {

        CreateStockRequest request = new CreateStockRequest();
        request.setName("car");
        request.setCurrentPrice(BigDecimal.valueOf(3456));

        Stock stockToSave = Stock.builder()
                .name(request.getName())
                .currentPrice(request.getCurrentPrice())
                .build();

        Stock savedStock = Stock.builder()
                .id(1L)
                .name(request.getName())
                .currentPrice(request.getCurrentPrice())
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();

        when(stockRepository.save(stockToSave)).thenReturn(savedStock);

        ApiResponse<StockResponse> response = stockService.create(request);

        assertNotNull(response);
        assertEquals(ResponseCode.STOCK_RESPONSE.getStatusCode(), response.getStatusCode());
        assertEquals(ResponseCode.STOCK_RESPONSE.getMessage(), response.getMessage());

        StockResponse stockResponseSave = response.getPayLoad();
        stockResponseSave.setName(savedStock.getName());
        stockResponseSave.setId(savedStock.getId());
        stockResponseSave.setCreateDate(savedStock.getCreateDate());
        stockResponseSave.setLastUpdate(savedStock.getLastUpdate());
        assertNotNull(stockResponseSave);
        assertEquals(savedStock.getId(), stockResponseSave.getId());
        assertEquals(savedStock.getName(), stockResponseSave.getName());
        assertEquals(savedStock.getCurrentPrice(), stockResponseSave.getCurrentPrice());
        assertEquals(savedStock.getCreateDate(), stockResponseSave.getCreateDate());
        assertEquals(savedStock.getLastUpdate(), stockResponseSave.getLastUpdate());
        verify(stockRepository, times(1)).save(stockToSave);
    }

    @Test
    void update() {
        UpdateStockRequest request = new UpdateStockRequest();
        request.setId(1L);
        request.setName("updatedCar");
        request.setCurrentPrice(BigDecimal.valueOf(4000));

        Stock existingStock = Stock.builder()
                .id(1L)
                .name("car")
                .currentPrice(BigDecimal.valueOf(3456))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();

        when(stockRepository.findById(request.getId())).thenReturn(java.util.Optional.of(existingStock));

        // Act
        ApiResponse<StockResponse> response = stockService.update(request);

        // Assert
        assertNotNull(response);
        assertEquals(ResponseCode.UPDATE_SUCCESS_RESPONSE.getStatusCode(), response.getStatusCode());
        assertEquals(ResponseCode.UPDATE_SUCCESS_RESPONSE.getMessage(), response.getMessage());

        StockResponse stockResponse = response.getPayLoad();
        assertNotNull(stockResponse);

        verify(stockRepository, times(1)).save(existingStock);

        assertEquals(request.getId(), stockResponse.getId());
        assertEquals(request.getName(), stockResponse.getName());
        assertEquals(request.getCurrentPrice(), stockResponse.getCurrentPrice());
        assertEquals(existingStock.getCreateDate(), stockResponse.getCreateDate());
        assertEquals(existingStock.getLastUpdate(), stockResponse.getLastUpdate());
    }

    @Test
    void getStock() {

        Long stockId = 1L;
        Stock existingStock = Stock.builder()
                .id(stockId)
                .name("car")
                .currentPrice(BigDecimal.valueOf(3456))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(existingStock));

        ApiResponse<StockResponse> response = stockService.getStock(stockId);

        assertNotNull(response);
        assertEquals(ResponseCode.STOCK_RESPONSE.getStatusCode(), response.getStatusCode());
        assertEquals(ResponseCode.UPDATE_SUCCESS_RESPONSE.getMessage(), response.getMessage());

        StockResponse stockResponse = response.getPayLoad();
        assertNotNull(stockResponse);
        assertEquals(existingStock.getId(), stockResponse.getId());
        assertEquals(existingStock.getName(), stockResponse.getName());
        assertEquals(existingStock.getCurrentPrice(), stockResponse.getCurrentPrice());
        assertEquals(existingStock.getCreateDate(), stockResponse.getCreateDate());
        assertEquals(existingStock.getLastUpdate(), stockResponse.getLastUpdate());

        verify(stockRepository, times(1)).findById(stockId);
    }

    @Test
    void getListOfStock() {
        Stock stock1 = Stock.builder()
                .name("car")
                .currentPrice(BigDecimal.valueOf(3456))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();

        Stock stock2 = Stock.builder()
                .name("phone")
                .currentPrice(BigDecimal.valueOf(500.0))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();

        List<Stock> stocks = Arrays.asList(stock1, stock2);

        when(stockRepository.findAll()).thenReturn(stocks);


        ApiResponse<List<StockResponse>> response = stockService.getListOfStock();


        assertNotNull(response);
        assertEquals(ResponseCode.STOCK_RESPONSE.getStatusCode(), response.getStatusCode());
        assertEquals(ResponseCode.UPDATE_SUCCESS_RESPONSE.getMessage(), response.getMessage());

        List<StockResponse> stockResponses = response.getPayLoad();
        assertNotNull(stockResponses);
        assertEquals(2, stockResponses.size());

        assertEquals(stock1.getName(), stockResponses.get(0).getName());
        assertEquals(stock1.getCurrentPrice(), stockResponses.get(0).getCurrentPrice());
        assertEquals(stock1.getCreateDate(), stockResponses.get(0).getCreateDate());
        assertEquals(stock1.getLastUpdate(), stockResponses.get(0).getLastUpdate());

        assertEquals(stock2.getName(), stockResponses.get(1).getName());
        assertEquals(stock2.getCurrentPrice(), stockResponses.get(1).getCurrentPrice());
        assertEquals(stock2.getCreateDate(), stockResponses.get(1).getCreateDate());
        assertEquals(stock2.getLastUpdate(), stockResponses.get(1).getLastUpdate());
        verify(stockRepository, times(1)).findAll();
    }
}