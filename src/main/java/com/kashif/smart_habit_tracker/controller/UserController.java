package com.kashif.smart_habit_tracker.controller;

import com.kashif.smart_habit_tracker.dto.request.UserRequest;
import com.kashif.smart_habit_tracker.dto.response.ApiResponse;
import com.kashif.smart_habit_tracker.dto.response.UserResponse;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.mapper.UserMapper;
import com.kashif.smart_habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getCurrentUser(){
        return ResponseEntity.ok(
                userService.getUser()
        );
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(ApiResponse.success(
                "User updated successfully",
                userService.updateUser(userRequest))
        );
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(){
        userService.deleteUser();
        return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
    }
}
