package ru.job4j.chat.service;

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
public record ChatService(MessageRepository messageRepository,
                          PersonRepository personRepository,
                          RoleRepository roleRepository,
                          RoomRepository roomRepository) {

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Person> findAllPerson() {
        return (List<Person>) personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person findByUserName(String name) {
        return personRepository.login(name);
    }

    public Person findByIdPerson(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public void deletePerson(int id) {
        if (findByIdPerson(id) == null) {
            throw new IllegalArgumentException("Данного пользователя не существует");
        }
        personRepository.delete(findByIdPerson(id));
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
}