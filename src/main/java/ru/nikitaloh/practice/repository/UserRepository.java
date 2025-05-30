package ru.nikitaloh.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nikitaloh.practice.model.UserDto;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDto, String> {

    @Query(value = "select * from users", nativeQuery = true)
    List<UserDto> getUsers();

    boolean existsByLogin(String login);

    UserDto findByLogin(String userId);
}
