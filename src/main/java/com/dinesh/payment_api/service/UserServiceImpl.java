package com.dinesh.payment_api.service;

import com.dinesh.payment_api.dto.LoginResponse;
import com.dinesh.payment_api.entity.Role;
import com.dinesh.payment_api.entity.User;
import com.dinesh.payment_api.exception.UserAlreadyExistsException;
import com.dinesh.payment_api.repository.UserRepository;
import com.dinesh.payment_api.security.jwt.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}