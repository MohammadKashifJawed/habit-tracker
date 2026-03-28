package com.kashif.smart_habit_tracker.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
