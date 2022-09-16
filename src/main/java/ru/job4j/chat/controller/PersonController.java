package ru.job4j.chat.controller;

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
    public Person create(@RequestBody Person person) {
        if (chatService.findByUserName(person.getLogin()) != null) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
        try {
            return chatService.savePerson(person);
        } catch (Exception e) {
            throw new IllegalArgumentException("Проверьте правильность заполнения имени");
        }

    }

    @GetMapping("/")
    public List<Person> findAll() {
        return chatService.findAllPerson();
    }

    @GetMapping("/{id}")
    public Person findPersonById(@PathVariable int id) {
        try {
            return chatService.findByIdPerson(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
    }

    @DeleteMapping("/drop/{id}")
    public void deletePerson(@PathVariable int id) {
        chatService.deletePerson(id);
    }
}
