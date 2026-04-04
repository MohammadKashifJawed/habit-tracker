package com.kashif.smart_habit_tracker.mapper;

import com.kashif.smart_habit_tracker.dto.request.RegisterRequest;
import com.kashif.smart_habit_tracker.dto.request.UserRequest;
import com.kashif.smart_habit_tracker.dto.response.UserResponse;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.entity.enums.Role;
import lombok.Data;

@Data
public class UserMapper {

    public static UserResponse userToUserResponse(User user){
        return UserResponse.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User registerRequestToUser(RegisterRequest request){
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.valueOf(request.getRole()));
        return user;
    }

    public static User userRequestToUser(UserRequest request){
        User user = new User();
        user.setId(request.getId());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}

