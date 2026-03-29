package com.kashif.smart_habit_tracker.controller;

import com.kashif.smart_habit_tracker.dto.response.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/me")
public class UserController {

    @GetMapping
    public String getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Logged in as: " + authentication.getName() +
                "\nRoles: " + authentication.getAuthorities();
    }
}
