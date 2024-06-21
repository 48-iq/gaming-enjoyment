package ru.ivanov.gaming_enjoyment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ivanov.gaming_enjoyment.converters.MessageConverter;
import ru.ivanov.gaming_enjoyment.dto.MessageDto;
import ru.ivanov.gaming_enjoyment.entities.Message;
import ru.ivanov.gaming_enjoyment.queries.IdPageQuery;
import ru.ivanov.gaming_enjoyment.repositories.MessageRepository;
import ru.ivanov.gaming_enjoyment.services.intrf.MessageService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageConverter messageConverter;
    private final MessageRepository messageRepository;
    @Override
    public MessageDto createMessage(MessageDto messageDto) {
        Message message = messageConverter.convertToEntity(messageDto);
        return messageConverter.convertToDto(messageRepository.save(message));
    }

    @Override
    public MessageDto updateMessage(MessageDto messageDto) {
        Message message = messageConverter.convertToEntity(messageDto);
        return messageConverter.convertToDto(messageRepository.save(message));
    }

    @Override
    public Page<MessageDto> getAllMessages(IdPageQuery query) {
        Page<Message> messagePage = messageRepository.getMessagesByChatId(query.getId(),
                PageRequest.of(query.getPage(), query.getSize()));

        return new PageImpl<MessageDto>(
                messagePage.getContent().stream()
                        .map(messageConverter::convertToDto)
                        .toList(),
                messagePage.getPageable(),
                messagePage.getTotalElements()
        );
    }

    @Override
    public void deleteMessageById(Integer id) {
        messageRepository.deleteById(id);
    }
}
