package com.kashif.smart_habit_tracker.controller;

import com.kashif.smart_habit_tracker.dto.request.HabitRequest;
import com.kashif.smart_habit_tracker.dto.response.ApiResponse;
import com.kashif.smart_habit_tracker.dto.response.HabitResponse;
import com.kashif.smart_habit_tracker.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/habit")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<ApiResponse<HabitResponse>> createHabit(@RequestBody @Valid HabitRequest request){
        return ResponseEntity.ok(ApiResponse.success(
                "Habit created",
                habitService.createHabit(request)
        ));
    }
}
