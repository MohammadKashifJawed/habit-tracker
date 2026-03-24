package com.kashif.smart_habit_tracker.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HabitTrackingResponse {
    private LocalDate date;
    private String status;
}
