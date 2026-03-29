package com.kashif.smart_habit_tracker.service;

import com.kashif.smart_habit_tracker.dto.response.UserResponse;

import java.util.List;

public interface AdminService {

    List<UserResponse> getAllUsers();
}
