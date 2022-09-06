package ru.job4j.chat.controller;

import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final ChatService chatService;

    public PersonController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping()
    public Person create(@RequestBody Person person) {
        return chatService.savePerson(person);
    }

    @GetMapping("/")
    public List<Person> findAll() {
        return chatService.findAllPerson();
    }

    @GetMapping("/{id}")
    public Person findPersonById(@RequestParam int id) {
        return chatService.findByIdPerson(id);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        chatService.deletePerson(id);
    }
}
