package com.igortullio.hering.adapter.controller;

import com.igortullio.hering.adapter.dto.input.UserDtoInput;
import com.igortullio.hering.adapter.dto.output.UserDtoOutput;
import com.igortullio.hering.adapter.mapper.UserMapper;
import com.igortullio.hering.core.domain.User;
import com.igortullio.hering.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController implements InterfaceController<UserDtoInput, UserDtoOutput> {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDtoOutput get(Long id) {
        User user = userService.find(id);
        return userMapper.userToUserDtoOutput(user);
    }

    @Override
    public UserDtoOutput post(UserDtoInput userDtoInput) {
        User user = userMapper.userDtoInputToUser(userDtoInput);
        return userMapper.userToUserDtoOutput(userService.save(user));
    }

    @Override
    public UserDtoOutput put(Long id, UserDtoInput userDtoInput) {
        User user = userMapper.userDtoInputToUser(userDtoInput);
        return userMapper.userToUserDtoOutput(userService.update(id, user));
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

}
