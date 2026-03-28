package com.kashif.smart_habit_tracker.controller;

import com.kashif.smart_habit_tracker.dto.request.LoginRequest;
import com.kashif.smart_habit_tracker.dto.request.RegisterRequest;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody @Validated RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.verifyLogin(loginRequest);
    }
}
