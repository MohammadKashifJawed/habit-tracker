package com.kashif.smart_habit_tracker.dto.request;

import lombok.Data;

@Data
public class HabitRequest {
    private String name;
    private String description;
    private String frequency;
    private Integer goal;
}
