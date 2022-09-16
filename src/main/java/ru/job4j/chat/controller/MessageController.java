package ru.job4j.chat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.service.ChatService;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final ChatService chatService;

    public MessageController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        if (message.getText() == null) {
            throw new NullPointerException("Нельзя отправлять пустоее сообщение");
        }
        return chatService.saveMessage(message);

    }
}
