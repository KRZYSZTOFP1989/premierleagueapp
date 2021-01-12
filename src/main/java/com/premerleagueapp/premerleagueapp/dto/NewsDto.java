package com.premerleagueapp.premerleagueapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime date;
    private String source;
    private Long userId;
}
