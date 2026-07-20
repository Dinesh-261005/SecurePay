package com.dinesh.payment_api.controller;

import com.dinesh.payment_api.dto.*;
import com.dinesh.payment_api.entity.User;
import com.dinesh.payment_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = userService.register(
                request.getEmail(),
                request.getPassword()
        );

        UserResponse response =
                new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getRole().name()
                );

        return new ApiResponse<>(true,
                "User Registered Successfully",
                response);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                userService.login(
                        request.getEmail(),
                        request.getPassword()
                );

        return new ApiResponse<>(true,
                "Login Successful",
                response);
    }
}