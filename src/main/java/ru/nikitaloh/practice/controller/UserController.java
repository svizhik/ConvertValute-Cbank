package ru.nikitaloh.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nikitaloh.practice.model.UserDto;
import ru.nikitaloh.practice.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/logins")
    public List<UserDto> getLogins() {
        return userService.getUsers();
    }

    // убрать метод, сохранять автоматически
    @PutMapping
    public boolean addLogin(@RequestParam UserDto user) {
        return userService.addUser(user).hasBody();
    }
}
