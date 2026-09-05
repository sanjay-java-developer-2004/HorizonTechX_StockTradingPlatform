package com.example.StockTrading.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StockTrading.DTO.PortfolioResponseDto;
import com.example.StockTrading.Service.Serviceinter.PortfolioService;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins="*")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(
            PortfolioService portfolioService) {

        this.portfolioService = portfolioService;
    }

  //  // Get user's portfolio
    @GetMapping("/{userId}")
    public ResponseEntity<List<PortfolioResponseDto>>
            getUserPortfolio(
                    @PathVariable Long userId) {

        return ResponseEntity.ok(
                portfolioService.getUserPortfolio(userId)
        );
    }

 //   // Get total portfolio value
    @GetMapping("/{userId}/value")
    public ResponseEntity<Double> calculatePortfolioValue(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                portfolioService.calculatePortfolioValue(userId)
        );
    }

  //  // Get total profit or loss
    @GetMapping("/{userId}/profit-loss")
    public ResponseEntity<Double> calculateProfitOrLoss(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                portfolioService.calculateProfitOrLoss(userId)
        );
    }
}
