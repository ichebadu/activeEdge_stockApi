package com.example.taskdemo.exercise3.controller;

import com.example.taskdemo.exercise3.dto.request.CreateStockRequest;
import com.example.taskdemo.exercise3.dto.request.UpdateStockRequest;
import com.example.taskdemo.exercise3.dto.response.ApiResponse;
import com.example.taskdemo.exercise3.dto.response.StockResponse;
import com.example.taskdemo.exercise3.service.StockService;
import com.example.taskdemo.exercise3.utils.url.stockUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
class StockControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void create() throws Exception {
        CreateStockRequest request = new CreateStockRequest();
        request.setName("car");
        request.setCurrentPrice(BigDecimal.valueOf(3456));
        request.setCreateDate(new Timestamp(System.currentTimeMillis()));
        request.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        StockResponse payload = new StockResponse(1L, "car", BigDecimal.valueOf(3456), null, null);
        ApiResponse<StockResponse> mockedApiResponse = new ApiResponse<>(payload, -695, "stock created");
        System.out.println(payload);
        when(stockService.create(any())).thenReturn(mockedApiResponse);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(stockUrl.STOCK_BASE_URL + stockUrl.CREATE_STOCK)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        System.out.println(resultActions.andReturn().getResponse().getStatus());

        // Assert the response
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(-695))
                .andExpect(jsonPath("$.message").value("stock created"))
                .andExpect(jsonPath("$.payLoad.name").value("car"))
                .andExpect(jsonPath("$.payLoad.currentPrice").value(3456));
    }



    @Test
    void update() throws Exception {
        UpdateStockRequest request = new UpdateStockRequest();
        request.setId(1L);
        request.setName("updatedCar");
        request.setCurrentPrice(BigDecimal.valueOf(5000));

        // Mock the service response
        StockResponse mockedResponse = new StockResponse(1L, "updatedCar", BigDecimal.valueOf(5000), null, null);
        ApiResponse<StockResponse> mockedApiResponse = new ApiResponse<>(mockedResponse, -123, "stock updated");

        // Mock the service behavior
        when(stockService.update(any())).thenReturn(mockedApiResponse);

        // Perform the request
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(stockUrl.STOCK_BASE_URL + stockUrl.UPDATE_STOCK)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Assert the response
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(-123))
                .andExpect(jsonPath("$.message").value("stock updated"))
                .andExpect(jsonPath("$.payLoad.name").value("updatedCar"))
                .andExpect(jsonPath("$.payLoad.currentPrice").value(5000));
    }

    @Test
    void getSingleStock() throws Exception {
        Long stockId = 1L;


        StockResponse mockedResponse = new StockResponse(1L, "car", BigDecimal.valueOf(3456), null, null);
        ApiResponse<StockResponse> mockedApiResponse = new ApiResponse<>(mockedResponse, -456, "stock retrieved");


        when(stockService.getStock(eq(stockId))).thenReturn(mockedApiResponse);


        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(stockUrl.STOCK_BASE_URL + stockUrl.GET_STOCK, stockId))
                .andDo(print());


        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(-456))
                .andExpect(jsonPath("$.message").value("stock retrieved"))
                .andExpect(jsonPath("$.payLoad.name").value("car"))
                .andExpect(jsonPath("$.payLoad.currentPrice").value(3456));
    }

    @Test
    void getListOfStock() throws Exception {
        // Mock the service response
        List<StockResponse> mockedResponses = Arrays.asList(
                new StockResponse(1L, "car", BigDecimal.valueOf(3456), null, null),
                new StockResponse(2L, "phone", BigDecimal.valueOf(5000), null, null)
        );
        ApiResponse<List<StockResponse>> mockedApiResponse = new ApiResponse<>(mockedResponses, -789, "list of stocks retrieved");

        // Mock the service behavior
        when(stockService.getListOfStock()).thenReturn(mockedApiResponse);

        // Perform the request
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(stockUrl.STOCK_BASE_URL + stockUrl.LIST_OF_STOCK))
                .andDo(print());

        // Assert the response
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(-789))
                .andExpect(jsonPath("$.message").value("list of stocks retrieved"))
                .andExpect(jsonPath("$.payLoad[0].name").value("car"))
                .andExpect(jsonPath("$.payLoad[1].name").value("phone"));
    }
}