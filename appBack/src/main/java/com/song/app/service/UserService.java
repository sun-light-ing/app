package com.song.app.service;

import com.song.app.entity.User;

public interface UserService {
    User findByUsername(String username);
    boolean register(User user);
    String login(String username, String password);
} 