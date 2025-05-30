package ru.nikitaloh.practice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserDto {

    public UserDto() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login", nullable = false)
    private String login;


    public UserDto(String userId) {
        this.login = userId;
    }


    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }
}
