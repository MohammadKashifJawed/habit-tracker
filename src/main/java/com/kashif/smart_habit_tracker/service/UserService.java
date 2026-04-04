package com.kashif.smart_habit_tracker.service;

import com.kashif.smart_habit_tracker.dto.request.UserRequest;
import com.kashif.smart_habit_tracker.dto.response.UserResponse;

public interface UserService {

    UserResponse updateUser(UserRequest userRequest);

    void deleteUser();

    UserResponse getUser();
}
