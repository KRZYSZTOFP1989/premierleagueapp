package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.Mail;
import com.premerleagueapp.premerleagueapp.backend.domain.User;
import com.premerleagueapp.premerleagueapp.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final SimpleEmailService simpleEmailService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(final Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(()
                -> new Exception("User not found"));
    }

    @Override
    public User createUser(final User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setSignUpDate(now);
        userRepository.save(user);
        simpleEmailService.send(new Mail(
                "",
                "User account has been created",
                "New user" + user.getNickname() + "Your account has been created"));
        return user;
    }

    @Override
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public String generateKey(final Long userId) throws Exception {
        User user = findById(userId);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 10;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String key = sb.toString();
        user.setPassword(key);
        userRepository.save(user);
        return alphabet;
    }
}
