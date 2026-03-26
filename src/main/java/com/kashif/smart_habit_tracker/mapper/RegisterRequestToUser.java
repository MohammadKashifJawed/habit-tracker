package com.kashif.smart_habit_tracker.mapper;

import com.kashif.smart_habit_tracker.dto.request.RegisterRequest;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.entity.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class RegisterRequestToUser {
    User user = new User();

    public User registerRequestToUser(RegisterRequest request){
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.valueOf(request.getRole()));
        return user;
    }
}
