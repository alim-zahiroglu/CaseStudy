package com.uydev.service;

import com.uydev.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();

    UserDto create(UserDto newUser);
}
