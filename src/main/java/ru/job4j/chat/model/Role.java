package ru.job4j.chat.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static Role of(String name) {
        Role role = new Role();
        role.name = name;
        return role;
    }
}
