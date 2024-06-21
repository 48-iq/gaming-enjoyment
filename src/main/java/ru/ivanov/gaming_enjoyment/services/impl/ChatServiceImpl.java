package ru.ivanov.gaming_enjoyment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.ChatConverter;
import ru.ivanov.gaming_enjoyment.dto.ChatDto;
import ru.ivanov.gaming_enjoyment.entities.Chat;
import ru.ivanov.gaming_enjoyment.exceptions.EntityNotFoundException;
import ru.ivanov.gaming_enjoyment.queries.IdPageQuery;
import ru.ivanov.gaming_enjoyment.queries.PageQuery;
import ru.ivanov.gaming_enjoyment.repositories.ChatRepository;
import ru.ivanov.gaming_enjoyment.repositories.UserRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.ChatService;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final ChatConverter chatConverter;
    @Override
    public ChatDto createChat(ChatDto chatDto) {
        Chat chat = chatConverter.convertToEntity(chatDto);
        chat.setUsers(userRepository.findAllById(chatDto.getUsers()));
        return null;
    }

    @Override
    public ChatDto updateChat(ChatDto chatDto) {
        Chat chat = chatConverter.convertToEntity(chatDto);
        chat.setUsers(userRepository.findAllById(chatDto.getUsers()));
        return chatConverter.convertToDto(chatRepository.save(chat));
    }

    @Override
    public ChatDto getChatById(Integer id) {
        return chatConverter.convertToDto(chatRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Chat " + id + " not found")));
    }

    @Override
    public Page<ChatDto> getAllChats(IdPageQuery query) {
        Page<Chat> chatPage = chatRepository.getChatsByUserId(query.getId(), PageRequest.of(query.getPage(), query.getSize()));
        return new PageImpl<ChatDto>(
                chatPage.getContent().stream()
                        .map(chatConverter::convertToDto)
                        .toList(),
                chatPage.getPageable(),
                chatPage.getTotalElements()
        );
    }

    @Override
    public void deleteChatById(Integer id) {

    }
}
