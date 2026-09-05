package com.example.StockTrading.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRequestDto {

    @NotBlank(message = "Stock Symbol is required")
    private String symbol;

    @NotBlank(message = "Company Name is required")
    private String companyName;

    @NotNull(message = "Current Price is required")
    @Positive(message = "Current Price must be greater than zero")
    private Double currentPrice;

    @NotNull(message = "Available Quantity is required")
    @PositiveOrZero(message = "Available Quantity must be greater than zero")
    private Integer availableQuantity;


}
