package com.example.StockTrading.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StockTrading.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

        List<Transaction> findByUserId(Long userId);

        List<Transaction> findByUserIdAndStockId( Long userId, Long stockId);
}