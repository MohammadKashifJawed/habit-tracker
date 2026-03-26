package com.kashif.smart_habit_tracker.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String userName;
    private String email;
    private String password;
    private String role;
}
