package com.premerleagueapp.premerleagueapp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {

    private Long id;
    private String title;
    private String content;
    private String source;
    private Long userId;
}
