package com.kashif.smart_habit_tracker.service.Impl;

import com.kashif.smart_habit_tracker.dto.request.UserRequest;
import com.kashif.smart_habit_tracker.dto.response.UserResponse;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.mapper.UserMapper;
import com.kashif.smart_habit_tracker.repository.UserRepository;
import com.kashif.smart_habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public UserResponse getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return UserMapper.userToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User with given id not found"));
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        return UserMapper.userToUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRepository.delete(user);
    }
}
