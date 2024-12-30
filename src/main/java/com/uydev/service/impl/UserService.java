package com.uydev.service.impl;
import com.uydev.dto.UserDto;
import com.uydev.entity.User;
import com.uydev.enums.Role;
import com.uydev.exception.DuplicateKeyException;
import com.uydev.mapper.MapperUtil;
import com.uydev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService implements com.uydev.service.UserService {
    private final UserRepository repository;
    private final MapperUtil mapper;

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = repository.findAllByIsDeleted(false);
        return users.stream()
                .map(user -> mapper.convert(user, new UserDto()))
                .toList();
    }

    @Override
    public UserDto create(UserDto newUser) {
        if (repository.existsByUserNameAndIsDeleted(newUser.getUserName(),false)){
            throw new DuplicateKeyException("User is already exist with userName: '" + newUser.getUserName() + "'");
        }
        User user = mapper.convert(newUser,new User());
        user.setRole(Role.valueOf(newUser.getRole().toUpperCase()));
        User savedUser =  repository.save(user);
        return mapper.convert(savedUser, new UserDto());
    }
}
