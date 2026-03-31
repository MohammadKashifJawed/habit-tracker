package com.kashif.smart_habit_tracker.service.Impl;

import com.kashif.smart_habit_tracker.dto.request.HabitRequest;
import com.kashif.smart_habit_tracker.dto.response.HabitResponse;
import com.kashif.smart_habit_tracker.entity.Habit;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.exception.AlreadyExistsException;
import com.kashif.smart_habit_tracker.mapper.HabitMapper;
import com.kashif.smart_habit_tracker.repository.HabitRepository;
import com.kashif.smart_habit_tracker.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public HabitResponse createHabit(HabitRequest request) {
        Habit newHabit = HabitMapper.habitRequestToHabit(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getDetails();
        List<Habit> userHabits = user.getHabits();
        if(!userHabits.isEmpty()){
            userHabits.forEach(habit -> {
                if (habit.getName().equals(newHabit.getName())){
                    throw new AlreadyExistsException(
                            "Habit already exists for this user", HttpStatus.CONFLICT
                    );
                }
            });
        }
        newHabit.setUser(user);
        userHabits.add(newHabit);
        return HabitMapper.habitToHabitResponse(habitRepository.save(newHabit));
    }
}
