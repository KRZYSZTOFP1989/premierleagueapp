package com.premerleagueapp.premerleagueapp.service;

import com.premerleagueapp.premerleagueapp.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User findById(final Long userid) throws Exception;
    User createUser(final User user) throws Exception;
    void deleteUser(final Long userId);
    String generateKey(final Long userId) throws Exception;
}
