package com.premerleagueapp.premerleagueapp.backend.scheduler;

import com.premerleagueapp.premerleagueapp.backend.repository.UserRepository;
import com.premerleagueapp.premerleagueapp.backend.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private UserRepository userRepository;

}
