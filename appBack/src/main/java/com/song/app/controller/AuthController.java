package com.song.app.controller;

//import com.song.app.annotation.RateLimit;
import com.song.app.entity.User;
import com.song.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.register(user)) {
            return ResponseEntity.ok("注册成功");
        }
        return ResponseEntity.badRequest().body("注册失败");
    }

    @PostMapping("/login")
//    @RateLimit(seconds = 5)
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("用户名或密码错误");
    }
} 