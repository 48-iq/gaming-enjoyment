package ru.ivanov.gaming_enjoyment.converters;

import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.ChatDto;
import ru.ivanov.gaming_enjoyment.entities.Chat;

import java.util.ArrayList;

@Component
public class ChatConverter {

    public ChatDto convertToDto(Chat chat) {
        if (chat.getUsers() == null) {
            chat.setUsers(new ArrayList<>());
        }
        return ChatDto.builder()
                .id(chat.getId())
                .title(chat.getTitle())
                .users(chat.getUsers().stream().map(user -> user.getId()).toList())
                .build();
    }

    public Chat convertToEntity(ChatDto chatDto) {
        return Chat.builder()
                .id(chatDto.getId())
                .title(chatDto.getTitle())
                .build();
    }

}
