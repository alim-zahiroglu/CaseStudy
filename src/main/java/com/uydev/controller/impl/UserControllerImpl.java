package com.uydev.controller.impl;

import com.uydev.controller.BaseResponseOk;
import com.uydev.controller.UserController;
import com.uydev.dto.ResponseWrapper;
import com.uydev.dto.UserDto;
import com.uydev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl extends BaseResponseOk implements UserController {
    private final UserService userService;
    @Override
    public ResponseWrapper<List<UserDto>> getAllUser() {
        return ok(userService.findAllUsers(),"Users are retrieved successfully");
    }

    @Override
    public ResponseWrapper<UserDto> createUser(UserDto newUser) {
        return ok(userService.create(newUser), "User is successfully created");
    }
}
