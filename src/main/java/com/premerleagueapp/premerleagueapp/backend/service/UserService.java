package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User findById(final Long userid) throws Exception;
    User createUser(final User user) throws Exception;
    void deleteUser(final Long userId);
    String generateKey(final Long userId) throws Exception;
}
