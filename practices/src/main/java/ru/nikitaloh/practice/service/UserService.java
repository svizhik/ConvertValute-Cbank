package ru.nikitaloh.practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nikitaloh.practice.controller.UserController;
import ru.nikitaloh.practice.model.UserDto;
import ru.nikitaloh.practice.model.web.Response;
import ru.nikitaloh.practice.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET > SELECT
    public List<UserDto> getUsers() {
        return userRepository.getUsers();
    }

    // PUT > INSERT (ADD)
    public ResponseEntity<Response> addUser(UserDto user) {

        if (user == null || user.getLogin() == null || user.getLogin().trim().isEmpty()) {
            logger.warn("Вы не можете добавить пустой логин.");
            return ResponseEntity.badRequest().build();
        }

        if (userRepository.existsByLogin(user.getLogin())) {
            logger.info("С возвращением, {}!", user.getLogin());
            return ResponseEntity.ok(
                    new Response("Логин успешно найден в Базе Данных.", user)
            );
        } else {
            userRepository.save(user);
            logger.info("Добро пожаловать в наш Конвертер Валют, {}!", user.getLogin());
            return ResponseEntity.ok(new Response("Новый логин успешно добавлен.", user));
        }
    }


}
