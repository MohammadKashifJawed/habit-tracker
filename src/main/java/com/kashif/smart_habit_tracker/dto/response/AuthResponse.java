package com.kashif.smart_habit_tracker.dto.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
}
