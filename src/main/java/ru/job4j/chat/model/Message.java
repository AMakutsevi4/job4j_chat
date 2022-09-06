package ru.job4j.chat.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public static Message of(String text) {
        Message message = new Message();
        message.text = text;
        return message;
    }
}
