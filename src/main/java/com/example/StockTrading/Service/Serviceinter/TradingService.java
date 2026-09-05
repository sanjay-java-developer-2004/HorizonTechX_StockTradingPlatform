package com.example.StockTrading.Service.Serviceinter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StockTrading.DTO.TradeRequestDto;
import com.example.StockTrading.Entity.Transaction;

@Service

public interface TradingService {

    Transaction buyStock(TradeRequestDto tradeRequestDto);

    Transaction sellStock(TradeRequestDto tradeRequestDto);

    List<Transaction> getUserTransactions(Long userId);
}
