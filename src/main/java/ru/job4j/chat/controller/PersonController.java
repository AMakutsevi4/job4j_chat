package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final ChatService chatService;

    private final BCryptPasswordEncoder encoder;

    public PersonController(ChatService chatService, BCryptPasswordEncoder encoder) {
        this.chatService = chatService;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        if (chatService.findByUserName(person.getLogin()) != null) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        chatService.savePerson(person);
    }

    @PostMapping()
    public ResponseEntity<Person> create(@RequestBody Person person) {
        if (chatService.findByUserName(person.getLogin()) != null) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
        try {
            chatService.savePerson(person);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new IllegalArgumentException("Проверьте правильность заполнения имени");
        }
    }

    @GetMapping("/")
    public ResponseEntity<Person> findAll() {
        chatService.findAllPerson();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable int id) {
        try {
            chatService.findByIdPerson(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
    }

    @DeleteMapping("/drop/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        chatService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
