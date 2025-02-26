package com.example.chat.controller;

import com.example.chat.entity.ChatMessage;
import com.example.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRestController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    // DB에서 roomName 컬럼만 distinct 추출
    @GetMapping("/rooms")
    public List<String> getChatRooms() {
        return chatService.findDistinctRoomNames();
    }
    // ✅ 특정 채팅방의 메시지 조회 (REST API)
    @GetMapping("/messages/{roomName}")
    public List<ChatMessage> getMessages(@PathVariable String roomName) {
        return chatService.getMessagesByRoom(roomName);
    }
    @DeleteMapping("/messages/{id}")
    public ChatMessage deleteMessage(@PathVariable String id) {
        ChatMessage updated = chatService.deleteMessage(id);
        simpMessagingTemplate.convertAndSend("/topic/messages", updated);
        return updated;
    }
}
