package com.example.StockTrading.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StockTrading.DTO.TradeRequestDto;
import com.example.StockTrading.Entity.Transaction;
import com.example.StockTrading.Service.Serviceinter.TradingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trading")
@CrossOrigin(origins="*")
public class TradingController {

    private final TradingService tradingService;

    public TradingController(TradingService tradingService) {
        this.tradingService = tradingService;
    }

   // // Buy Stock
    @PostMapping("/buy")
    public ResponseEntity<Transaction> buyStock( @Valid @RequestBody TradeRequestDto dto) {

        return ResponseEntity.ok(tradingService.buyStock(dto));
    }

 //   // Sell Stock
    @PostMapping("/sell")
    public ResponseEntity<Transaction> sellStock( @Valid @RequestBody TradeRequestDto dto) {

        return ResponseEntity.ok( tradingService.sellStock(dto));
    }

  //  //transaction
    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<Transaction>> getUserTransactions( @PathVariable Long userId) {

        return ResponseEntity.ok( tradingService.getUserTransactions(userId));
    }
}