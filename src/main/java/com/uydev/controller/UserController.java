package com.uydev.controller;

import com.uydev.dto.ResponseWrapper;
import com.uydev.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "Operations related to users")
public interface UserController {
    @GetMapping(value = "/list")
    @Operation(summary = "Get all users")
    ResponseWrapper<List<UserDto>> getAllUser();

    @PostMapping("/create")
    @Operation(summary = "Create a new user")
    ResponseWrapper<UserDto> createUser(@Valid @RequestBody UserDto newUser);

}
