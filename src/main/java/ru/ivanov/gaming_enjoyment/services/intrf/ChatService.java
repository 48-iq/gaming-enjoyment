package ru.ivanov.gaming_enjoyment.services.intrf;

import org.springframework.data.domain.Page;
import ru.ivanov.gaming_enjoyment.dto.ChatDto;
import ru.ivanov.gaming_enjoyment.queries.IdPageQuery;

public interface ChatService {
    ChatDto createChat(ChatDto chatDto);
    ChatDto updateChat(ChatDto chatDto);
    ChatDto getChatById(Integer id);
    Page<ChatDto> getAllChats(IdPageQuery query);

    void deleteChatById(Integer id);
}
