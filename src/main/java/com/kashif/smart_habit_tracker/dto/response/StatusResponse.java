package com.kashif.smart_habit_tracker.dto.response;

import lombok.Data;

@Data
public class StatusResponse {
    private int totalDays;
    private int completedDays;
    private double successRate;
}
