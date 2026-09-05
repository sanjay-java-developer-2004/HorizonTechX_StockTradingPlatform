package com.example.StockTrading.DTO;

import com.example.StockTrading.Enum.PaymentStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBalanceRequestDto {

    @NotNull(message = "Somthing Wrong Pls Login Again")
    @PositiveOrZero(message = "Somthing Wrong")
    private Long id;

    @NotNull(message = "Current Price is required")
    @Positive(message = "Current Price must be greater than zero")
    private Double amount;

    private PaymentStatus paymentStatus;

}
