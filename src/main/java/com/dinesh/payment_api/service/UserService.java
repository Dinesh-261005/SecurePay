package com.dinesh.payment_api.service;

import com.dinesh.payment_api.dto.LoginResponse;
import com.dinesh.payment_api.entity.User;

public interface UserService {

    User register(String email, String password);

    LoginResponse login(String email, String password);
}