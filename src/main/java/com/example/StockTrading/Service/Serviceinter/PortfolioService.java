package com.example.StockTrading.Service.Serviceinter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StockTrading.DTO.PortfolioResponseDto;

@Service

public interface PortfolioService {

    List<PortfolioResponseDto> getUserPortfolio(Long userId);

    Double calculatePortfolioValue(Long userId);

    Double calculateProfitOrLoss(Long userId);
}