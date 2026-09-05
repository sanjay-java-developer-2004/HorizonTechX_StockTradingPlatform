package com.example.StockTrading.Service.ServiceImplement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StockTrading.DTO.StockRequestDto;
import com.example.StockTrading.Entity.Stock;
import com.example.StockTrading.Repository.StockRepository;
import com.example.StockTrading.Service.Serviceinter.StockService;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock createStock(StockRequestDto dto) {

        if (stockRepository.findBySymbol(dto.getSymbol()).isPresent()) {
            throw new RuntimeException("Stock symbol already exists");
        }

        Stock stock = new Stock();

        stock.setSymbol(dto.getSymbol());
        stock.setCompanyName(dto.getCompanyName());
        stock.setCurrentPrice(dto.getCurrentPrice());
        stock.setAvailableQuantity(dto.getAvailableQuantity());

        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long stockId, StockRequestDto dto) {

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() ->
                        new RuntimeException("Stock not found"));

        stock.setSymbol(dto.getSymbol());
        stock.setCompanyName(dto.getCompanyName());
        stock.setCurrentPrice(dto.getCurrentPrice());
        stock.setAvailableQuantity(dto.getAvailableQuantity());

        return stockRepository.save(stock);
    }

    @Override
    public Stock getStockById(Long stockId) {

        return stockRepository.findById(stockId)
                .orElseThrow(() ->
                        new RuntimeException("Stock not found"));
    }

    @Override
    public Stock getStockBySymbol(String symbol) {

        return stockRepository.findBySymbol(symbol)
                .orElseThrow(() ->
                        new RuntimeException("Stock not found"));
    }

    @Override
    public List<Stock> getAllStocks() {

        return stockRepository.findAll();
    }
}