package com.kashif.smart_habit_tracker.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String userName;
    private String email;
    private String password;
}
