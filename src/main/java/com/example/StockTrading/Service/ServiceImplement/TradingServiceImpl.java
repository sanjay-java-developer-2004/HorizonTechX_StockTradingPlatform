package com.example.StockTrading.Service.ServiceImplement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StockTrading.DTO.TradeRequestDto;
import com.example.StockTrading.Entity.Portfolio;
import com.example.StockTrading.Entity.Stock;
import com.example.StockTrading.Entity.Transaction;
import com.example.StockTrading.Entity.User;
import com.example.StockTrading.Enum.TransactionType;
import com.example.StockTrading.Exception.UserNotFoundException;
import com.example.StockTrading.Repository.PortfolioRepository;
import com.example.StockTrading.Repository.StockRepository;
import com.example.StockTrading.Repository.TransactionRepository;
import com.example.StockTrading.Repository.UserRepository;
import com.example.StockTrading.Service.Serviceinter.TradingService;

import jakarta.transaction.Transactional;

@Service
public class TradingServiceImpl implements TradingService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final PortfolioRepository portfolioRepository;
    private final TransactionRepository transactionRepository;

    public TradingServiceImpl(
            UserRepository userRepository,
            StockRepository stockRepository,
            PortfolioRepository portfolioRepository,
            TransactionRepository transactionRepository) {

        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
        this.portfolioRepository = portfolioRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Transaction buyStock(TradeRequestDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Stock stock = stockRepository.findById(dto.getStockId())
                .orElseThrow(() ->
                        new RuntimeException("Stock not found"));

        if (dto.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        if (stock.getAvailableQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Insufficient stock quantity");
        }

        double totalAmount =
                stock.getCurrentPrice() * dto.getQuantity();

        if (user.getBalance() < totalAmount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Deduct money from user
        user.setBalance(user.getBalance() - totalAmount);

        // Reduce available stock
        stock.setAvailableQuantity(
                stock.getAvailableQuantity() - dto.getQuantity()
        );

        // Update portfolio
        Portfolio portfolio =
                portfolioRepository
                        .findByUserIdAndStockId(
                                dto.getUserId(),
                                dto.getStockId()
                        )
                        .orElse(null);

        if (portfolio == null) {

            portfolio = new Portfolio();

            portfolio.setUser(user);
            portfolio.setStock(stock);
            portfolio.setQuantity(dto.getQuantity());
            portfolio.setAverageBuyPrice(stock.getCurrentPrice());

        } else {

            int oldQuantity = portfolio.getQuantity();

            double oldInvestment =
                    oldQuantity * portfolio.getAverageBuyPrice();

            int newQuantity =
                    oldQuantity + dto.getQuantity();

            double newInvestment =
                    oldInvestment + totalAmount;

            double newAveragePrice =
                    newInvestment / newQuantity;

            portfolio.setQuantity(newQuantity);
            portfolio.setAverageBuyPrice(newAveragePrice);
        }

        portfolioRepository.save(portfolio);
        userRepository.save(user);
        stockRepository.save(stock);

        // Create transaction
        Transaction transaction = new Transaction();

        transaction.setUser(user);
        transaction.setStock(stock);
        transaction.setType(TransactionType.BUY);
        transaction.setQuantity(dto.getQuantity());
        transaction.setPrice(stock.getCurrentPrice());
        transaction.setTotalAmount(totalAmount);
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

   @Override
@Transactional
public Transaction sellStock(TradeRequestDto dto) {

    User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    Stock stock = stockRepository.findById(dto.getStockId())
            .orElseThrow(() ->
                    new RuntimeException("Stock not found"));

    if (dto.getQuantity() <= 0) {
        throw new RuntimeException(
                "Quantity must be greater than zero");
    }

    Portfolio portfolio = portfolioRepository
            .findByUserIdAndStockId(
                    dto.getUserId(),
                    dto.getStockId()
            )
            .orElseThrow(() ->
                    new RuntimeException(
                            "Stock not available in portfolio"));

    if (portfolio.getQuantity() < dto.getQuantity()) {
        throw new RuntimeException(
                "Insufficient stock quantity in portfolio");
    }

    double totalAmount =
            stock.getCurrentPrice() * dto.getQuantity();

    // Add selling amount to user balance
    user.setBalance(
            user.getBalance() + totalAmount
    );

    // Reduce portfolio quantity
    int remainingQuantity =
            portfolio.getQuantity() - dto.getQuantity();

    portfolio.setQuantity(remainingQuantity);

    // Increase market stock quantity
    stock.setAvailableQuantity(
            stock.getAvailableQuantity()
                    + dto.getQuantity()
    );

    // If all shares are sold, remove portfolio entry
    if (remainingQuantity == 0) {
        portfolioRepository.delete(portfolio);
    } else {
        portfolioRepository.save(portfolio);
    }

    userRepository.save(user);
    stockRepository.save(stock);

    // Create SELL transaction
    Transaction transaction = new Transaction();

    transaction.setUser(user);
    transaction.setStock(stock);
    transaction.setType(TransactionType.SELL);
    transaction.setQuantity(dto.getQuantity());
    transaction.setPrice(stock.getCurrentPrice());
    transaction.setTotalAmount(totalAmount);
    transaction.setTransactionDate(LocalDateTime.now());

    return transactionRepository.save(transaction);
}


@Override
public List<Transaction> getUserTransactions(Long userId) {

    userRepository.findById(userId)
            .orElseThrow(() ->
                    new UserNotFoundException("User not found"));

    return transactionRepository.findByUserId(userId);
}
}