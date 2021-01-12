package com.premerleagueapp.premerleagueapp.mapper;

import com.premerleagueapp.premerleagueapp.domain.User;
import com.premerleagueapp.premerleagueapp.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final NewsMapper newsMapper;

    public User MapToUser(final UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setSignUpDate(userDto.getSignUpDate());
        return user;
    }

    public UserDto MapToUserDto(final User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNickname(user.getNickname());
        userDto.setEmail(user.getEmail());
        userDto.setSignUpDate(user.getSignUpDate());
        userDto.setNews(newsMapper.MapToNewsDtoList(user.getNews()));
        return userDto;
    }

    public List<UserDto> MapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(this::MapToUserDto)
                .collect(Collectors.toList());
    }
}
