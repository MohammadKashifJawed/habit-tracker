package com.kashif.smart_habit_tracker.service.Impl;

import com.kashif.smart_habit_tracker.dto.request.RegisterRequest;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.exception.UserAlreadyExistsException;
import com.kashif.smart_habit_tracker.mapper.RegisterRequestToUser;
import com.kashif.smart_habit_tracker.repository.UserRepository;
import com.kashif.smart_habit_tracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RegisterRequestToUser mapToUser;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public User registerUser(RegisterRequest registerRequest) {

        //Checking if provided user already exists
        User existingUserByName = userRepository.findByUsername(registerRequest.getUserName());
        if(existingUserByName != null){
            throw new UserAlreadyExistsException("Username already exist", HttpStatus.CONFLICT);
        }
        User existingUserByEmail = userRepository.findByEmail(registerRequest.getEmail());
        if(existingUserByEmail != null){
            throw new UserAlreadyExistsException("Email already exist", HttpStatus.CONFLICT);
        }

        //Saving user to db
        User user = mapToUser.registerRequestToUser(registerRequest);
        return userRepository.save(user);
    }
}
