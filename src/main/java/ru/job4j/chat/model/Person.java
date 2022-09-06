package ru.job4j.chat.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Message> messages = new ArrayList<>();

    public static Person of(String login, String password) {
        Person person = new Person();
        person.login = login;
        person.password = password;
        return person;
    }
}
