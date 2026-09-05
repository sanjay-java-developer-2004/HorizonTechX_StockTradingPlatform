package com.example.StockTrading.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String errorCode;

    public ErrorResponse(
            LocalDateTime timestamp,
            String message,
            String errorCode) {

        this.timestamp = timestamp;
        this.message = message;
        this.errorCode = errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
