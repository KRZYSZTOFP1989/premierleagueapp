package com.premerleagueapp.premerleagueapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String nickname;
    private String email;
    private String password;
    private LocalDateTime signUpDate;
    private List<NewsDto> news = new ArrayList<>();
}
