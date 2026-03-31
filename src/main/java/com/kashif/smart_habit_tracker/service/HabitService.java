package com.kashif.smart_habit_tracker.service;

import com.kashif.smart_habit_tracker.dto.request.HabitRequest;
import com.kashif.smart_habit_tracker.dto.response.ApiResponse;
import com.kashif.smart_habit_tracker.dto.response.HabitResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface HabitService {
    HabitResponse createHabit(@Valid HabitRequest request);
}
