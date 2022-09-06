package ru.job4j.chat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.ChatService;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final ChatService chatService;

    public RoomController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return chatService.saveRoom(room);
    }
}
