package com.premerleagueapp.premerleagueapp.backend.controller;

import com.premerleagueapp.premerleagueapp.backend.dto.UserDto;
import com.premerleagueapp.premerleagueapp.backend.mapper.UserMapper;
import com.premerleagueapp.premerleagueapp.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public List<UserDto> getUsers() {
        return userMapper.MapToUserDtoList(userService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public UserDto getUser(@PathVariable Long userId) throws Exception {
        return userMapper.MapToUserDto(userService.findById(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "user", consumes = APPLICATION_JSON_VALUE)
    public void saveUser(@RequestBody UserDto userDto) throws Exception {
        userService.createUser(userMapper.MapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "user/key")
    public String generateUserKey(@RequestParam Long userId) throws Exception {
        return userService.generateKey(userId);
    }
}
