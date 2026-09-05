package com.example.StockTrading.Service.Serviceinter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StockTrading.DTO.StockRequestDto;
import com.example.StockTrading.Entity.Stock;

@Service
public interface StockService {

    Stock createStock(StockRequestDto stockRequestDto);

    Stock updateStock(Long stockId, StockRequestDto stockRequestDto);

    Stock getStockById(Long stockId);

    Stock getStockBySymbol(String symbol);

    List<Stock> getAllStocks();
}