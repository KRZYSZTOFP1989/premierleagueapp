package com.premerleagueapp.premerleagueapp.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Mail {

    private String mailTo;
    private String toCc;
    private String subject;
    private String message;

    public Mail(String mailTo, String user_account_has_been_created, String subject) {
    }
}
