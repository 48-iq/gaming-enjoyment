package ru.ivanov.gaming_enjoyment.services.intrf;

import org.springframework.data.domain.Page;
import ru.ivanov.gaming_enjoyment.dto.MessageDto;
import ru.ivanov.gaming_enjoyment.queries.IdPageQuery;

public interface MessageService {

    MessageDto createMessage(MessageDto messageDto);
    MessageDto updateMessage(MessageDto messageDto);
    Page<MessageDto> getAllMessages(IdPageQuery query);
    void deleteMessageById(Integer id);
}
