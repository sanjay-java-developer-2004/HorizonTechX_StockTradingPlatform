package com.example.StockTrading.Service.Serviceinter;

import org.springframework.stereotype.Service;

import com.example.StockTrading.DTO.AddBalanceRequestDto;
import com.example.StockTrading.DTO.LoginResponseDto;
import com.example.StockTrading.DTO.RegisterRequestDto;
import com.example.StockTrading.Entity.User;

@Service
public interface UserService {

    String createUser(RegisterRequestDto dto);

    User getUserById(Long userId);

    User getUserByEmail(String email);

    LoginResponseDto login(String email, String password);

    String addBalance(AddBalanceRequestDto request) throws Exception ;
}