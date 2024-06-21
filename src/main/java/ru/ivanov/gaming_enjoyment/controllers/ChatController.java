package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.ChatDto;
import ru.ivanov.gaming_enjoyment.queries.IdPageQuery;
import ru.ivanov.gaming_enjoyment.services.intrf.ChatService;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/all")
    public ResponseEntity<Page<ChatDto>> getAllChats(@RequestParam("id") Integer id,
                                                    @RequestParam("page") Integer page,
                                                    @RequestParam("size") Integer size) {
        IdPageQuery idPageQuery = IdPageQuery.builder().id(id).page(page).size(size).build();
        return ResponseEntity.ok(chatService.getAllChats(idPageQuery));
    }

    @PostMapping("/new")
    public ResponseEntity<ChatDto> createChat(@RequestBody ChatDto chatDto) {
        return ResponseEntity.ok(chatService.createChat(chatDto));
    }

    @PutMapping("/update")
    public ResponseEntity<ChatDto> updateChat(@RequestBody ChatDto chatDto) {
        return ResponseEntity.ok(chatService.updateChat(chatDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteChat(@RequestParam("id") Integer id) {
        chatService.deleteChatById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatDto> getChatById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(chatService.getChatById(id));
    }

}
