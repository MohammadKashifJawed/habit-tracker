package com.kashif.smart_habit_tracker.service;

import com.kashif.smart_habit_tracker.dto.request.RegisterRequest;
import com.kashif.smart_habit_tracker.entity.User;

public interface AuthService {

    User registerUser(RegisterRequest registerRequest);
}
