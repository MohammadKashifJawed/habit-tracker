package com.kashif.smart_habit_tracker.filter;

import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.repository.UserRepository;
import com.kashif.smart_habit_tracker.service.AuthService;
import com.kashif.smart_habit_tracker.service.Impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = bearerToken.substring(7);
        String userName = jwtService.getUserNameFromToken(token);

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = userRepository.findByUsername(userName);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    null
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);
        }

    }
}
