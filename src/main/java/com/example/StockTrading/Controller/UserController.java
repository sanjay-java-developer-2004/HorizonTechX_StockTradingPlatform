package com.example.StockTrading.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StockTrading.DTO.AddBalanceRequestDto;
import com.example.StockTrading.DTO.LoginRequestDto;
import com.example.StockTrading.DTO.LoginResponseDto;
import com.example.StockTrading.DTO.RegisterRequestDto;
import com.example.StockTrading.Entity.User;
import com.example.StockTrading.Service.Serviceinter.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   // // Create User
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody RegisterRequestDto dto) {

        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

  //  //login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {

        LoginResponseDto response = userService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(response);
    }

  //  // Get User by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {

        return ResponseEntity.ok(userService.getUserById(userId));
    }

    // Get User by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {

        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

  //  //add balance
    @PatchMapping("/addbalance")
    public ResponseEntity<String> addBalance(@Valid @RequestBody AddBalanceRequestDto request) throws Exception {
        return ResponseEntity.ok(userService.addBalance(request));
    }

}
