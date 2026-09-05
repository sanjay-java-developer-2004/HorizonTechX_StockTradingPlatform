package com.example.StockTrading.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StockTrading.DTO.StockRequestDto;
import com.example.StockTrading.Entity.Stock;
import com.example.StockTrading.Service.Serviceinter.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins="*")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // Create Stock
    @PostMapping("/create")
    public ResponseEntity<Stock> createStock(
          @Valid  @RequestBody StockRequestDto dto) {

        Stock stock = stockService.createStock(dto);

        return new ResponseEntity<>(
                stock,
                HttpStatus.CREATED
        );
    }

    // Update Stock
    @PutMapping("/{stockId}")
    public ResponseEntity<Stock> updateStock(
            @PathVariable Long stockId,
            @Valid @RequestBody StockRequestDto dto) {

        return ResponseEntity.ok(
                stockService.updateStock(stockId, dto)
        );
    }

    // Get Stock by ID
    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> getStockById(
            @PathVariable Long stockId) {

        return ResponseEntity.ok(
                stockService.getStockById(stockId)
        );
    }

    // Get Stock by Symbol
    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<Stock> getStockBySymbol(
            @PathVariable String symbol) {

        return ResponseEntity.ok(
                stockService.getStockBySymbol(symbol)
        );
    }

 //   // Get All Stocks
    @GetMapping("/getall")
    public ResponseEntity<List<Stock>> getAllStocks() {

        return ResponseEntity.ok(
                stockService.getAllStocks()
        );
    }
}