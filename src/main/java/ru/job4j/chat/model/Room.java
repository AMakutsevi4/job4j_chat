package ru.job4j.chat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany()
    @JoinTable(name = "room_person", joinColumns = {
            @JoinColumn(name = "room_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "person_id")})
    private Set<Person> people = new HashSet<>();

    public static Room of(String name) {
        Room room = new Room();
        room.name = name;
        return room;
    }
}
