package com.example.StockTrading.Service.ServiceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StockTrading.DTO.PortfolioResponseDto;
import com.example.StockTrading.Entity.Portfolio;
import com.example.StockTrading.Repository.PortfolioRepository;
import com.example.StockTrading.Repository.UserRepository;
import com.example.StockTrading.Service.Serviceinter.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    public PortfolioServiceImpl(
            PortfolioRepository portfolioRepository,
            UserRepository userRepository) {

        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PortfolioResponseDto> getUserPortfolio(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Portfolio> portfolios =
                portfolioRepository.findByUserId(userId);

        List<PortfolioResponseDto> response =
                new ArrayList<>();

        for (Portfolio portfolio : portfolios) {

            double currentValue =
                    portfolio.getQuantity()
                    * portfolio.getStock().getCurrentPrice();

            double investmentValue =
                    portfolio.getQuantity()
                    * portfolio.getAverageBuyPrice();

            double profitOrLoss =
                    currentValue - investmentValue;

            PortfolioResponseDto dto =
                    new PortfolioResponseDto();

            dto.setSymbol(
                    portfolio.getStock().getSymbol()
            );

            dto.setCompanyName(
                    portfolio.getStock().getCompanyName()
            );

            dto.setQuantity(
                    portfolio.getQuantity()
            );

            dto.setAverageBuyPrice(
                    portfolio.getAverageBuyPrice()
            );

            dto.setCurrentPrice(
                    portfolio.getStock().getCurrentPrice()
            );

            dto.setCurrentValue(currentValue);

            dto.setProfitOrLoss(profitOrLoss);

            response.add(dto);
        }

        return response;
    }

    @Override
    public Double calculatePortfolioValue(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Portfolio> portfolios =
                portfolioRepository.findByUserId(userId);

        double totalValue = 0;

        for (Portfolio portfolio : portfolios) {

            double currentValue =
                    portfolio.getQuantity()
                    * portfolio.getStock().getCurrentPrice();

            totalValue += currentValue;
        }

        return totalValue;
    }

    @Override
    public Double calculateProfitOrLoss(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Portfolio> portfolios =
                portfolioRepository.findByUserId(userId);

        double totalProfitOrLoss = 0;

        for (Portfolio portfolio : portfolios) {

            double currentValue =
                    portfolio.getQuantity()
                    * portfolio.getStock().getCurrentPrice();

            double investmentValue =
                    portfolio.getQuantity()
                    * portfolio.getAverageBuyPrice();

            totalProfitOrLoss +=
                    currentValue - investmentValue;
        }

        return totalProfitOrLoss;
    }
}
