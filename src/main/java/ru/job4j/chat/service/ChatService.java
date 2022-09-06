package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.MessageRepository;
import ru.job4j.chat.repository.PersonRepository;
import ru.job4j.chat.repository.RoleRepository;
import ru.job4j.chat.repository.RoomRepository;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Person> findAllPerson() {
        return (List<Person>) personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person findByIdPerson(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public void deletePerson(int id) {
        Person person = new Person();
        person.setId(id);
        personRepository.delete(person);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
}