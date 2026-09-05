package com.example.StockTrading.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponseDto {

    private String symbol;
    private String companyName;
    private Integer quantity;
    private Double averageBuyPrice;
    private Double currentPrice;
    private Double currentValue;
    private Double profitOrLoss;


}
