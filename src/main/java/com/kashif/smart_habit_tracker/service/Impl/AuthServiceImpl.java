package com.kashif.smart_habit_tracker.service.Impl;

import com.kashif.smart_habit_tracker.dto.request.LoginRequest;
import com.kashif.smart_habit_tracker.dto.request.RegisterRequest;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.exception.UserAlreadyExistsException;
import com.kashif.smart_habit_tracker.mapper.UserMapper;
import com.kashif.smart_habit_tracker.repository.UserRepository;
import com.kashif.smart_habit_tracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

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
        User user = UserMapper.registerRequestToUser(registerRequest);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String verifyLogin(LoginRequest loginRequest) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (loginRequest.getUserName(), loginRequest.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(loginRequest.getUserName());
        return "Wrong Credentials";
    }

}
