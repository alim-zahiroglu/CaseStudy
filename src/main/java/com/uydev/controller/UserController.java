package com.uydev.controller;

import com.uydev.dto.ResponseWrapper;
import com.uydev.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/user")
public interface UserController {
    @GetMapping(value = "/list")
    ResponseWrapper<List<UserDto>> getAllUser();

    @PostMapping("/create")
    ResponseWrapper<UserDto> createUser(@Valid @RequestBody UserDto newUser);

}
