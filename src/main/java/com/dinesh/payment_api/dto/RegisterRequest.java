package com.dinesh.payment_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Email required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 4, message = "Password must be atleast 4 characters")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}