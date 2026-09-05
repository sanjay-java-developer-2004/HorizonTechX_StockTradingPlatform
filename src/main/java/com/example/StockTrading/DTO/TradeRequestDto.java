package com.example.StockTrading.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TradeRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Stock ID is required")
    private Long stockId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

}
