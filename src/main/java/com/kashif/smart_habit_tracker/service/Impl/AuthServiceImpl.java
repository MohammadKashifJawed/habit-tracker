package com.kashif.smart_habit_tracker.service.Impl;

import com.kashif.smart_habit_tracker.dto.request.RegisterRequest;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.mapper.RegisterRequestToUser;
import com.kashif.smart_habit_tracker.repository.UserRepository;
import com.kashif.smart_habit_tracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RegisterRequestToUser mapToUser;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User registerUser(RegisterRequest registerRequest) {
        User user = mapToUser.registerRequestToUser(registerRequest);
        return userRepository.save(user);
    }
}
