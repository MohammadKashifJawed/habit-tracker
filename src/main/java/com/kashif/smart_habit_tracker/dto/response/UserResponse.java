package com.kashif.smart_habit_tracker.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private String userName;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}
