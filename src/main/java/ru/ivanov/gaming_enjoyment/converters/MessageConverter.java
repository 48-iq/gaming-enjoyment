package ru.ivanov.gaming_enjoyment.converters;

import org.springframework.stereotype.Component;
import ru.ivanov.gaming_enjoyment.dto.MessageDto;
import ru.ivanov.gaming_enjoyment.entities.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MessageConverter {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MessageDto convertToDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .authorId(message.getAuthor().getId())
                .chatId(message.getChat().getId())
                .time(message.getTime().toString())
                .build();
    }

    public Message convertToEntity(MessageDto messageDto) {
        return Message.builder()
                .id(messageDto.getId())
                .content(messageDto.getContent())
                .time(LocalDateTime.parse(messageDto.getTime(), formatter))
                .build();
    }
}
