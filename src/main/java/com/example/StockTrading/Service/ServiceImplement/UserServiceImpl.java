package com.example.StockTrading.Service.ServiceImplement;

import org.springframework.stereotype.Service;

import com.example.StockTrading.DTO.AddBalanceRequestDto;
import com.example.StockTrading.DTO.LoginResponseDto;
import com.example.StockTrading.DTO.RegisterRequestDto;
import com.example.StockTrading.Entity.User;
import com.example.StockTrading.Enum.PaymentStatus;
import com.example.StockTrading.Exception.UserNotFoundException;
import com.example.StockTrading.Repository.UserRepository;
import com.example.StockTrading.Service.Serviceinter.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // register
    @Override
    public String createUser(RegisterRequestDto dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBalance(0.0);

         userRepository.save(user);
        return "Registred Successfully";
    }



    // login user
    @Override
    public LoginResponseDto login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException( "User not found"));

        if (!user.getPassword().equals(password)) {

            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    // get user
    @Override
    public User getUserById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    
    // getuser by email
    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //add amount
    
    @Override
    @Transactional
    public String addBalance(AddBalanceRequestDto request) throws Exception {
       if(request.getAmount() <=0 || request.getId() == null ) {
        throw new RuntimeException("Amount must greater then 0");
       }
       User user = getUserById(request.getId());

       if(user.getId() == request.getId() && request.getPaymentStatus().equals(PaymentStatus.Success)){
         user.setBalance((user.getBalance()+request.getAmount()));
        //  user.setBalance((0.0+request.getAmount()));
       }else{
        throw new Exception("Payment Faild Try Again");
       }

       userRepository.save(user);

       return "Amounted Add Successfully";
    
    }

}