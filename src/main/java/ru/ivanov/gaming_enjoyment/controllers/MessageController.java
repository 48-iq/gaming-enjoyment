package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.MessageDto;
import ru.ivanov.gaming_enjoyment.queries.IdPageQuery;
import ru.ivanov.gaming_enjoyment.services.intrf.MessageService;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/all")
    public ResponseEntity<Page<MessageDto>> getAllMessages(@RequestParam("id") Integer id,
                                                           @RequestParam("page") Integer page,
                                                           @RequestParam("size") Integer size) {
        IdPageQuery idPageQuery = IdPageQuery.builder().id(id).page(page).size(size).build();
        return ResponseEntity.ok(messageService.getAllMessages(idPageQuery));
    }

    @PostMapping("/new")
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(messageService.createMessage(messageDto));
    }

    @PutMapping("/update")
    public ResponseEntity<MessageDto> updateMessage(@RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(messageService.updateMessage(messageDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMessage(@RequestParam("id") Integer id) {
        messageService.deleteMessageById(id);
        return ResponseEntity.ok().build();
    }
}
