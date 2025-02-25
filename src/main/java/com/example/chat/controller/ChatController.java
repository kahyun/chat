package com.example.chat.controller;

import com.example.chat.entity.ChatMessage;
import com.example.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;


    @GetMapping("/chat-test")
    public String serveChatTestHtml() {
       return "chat-test";
    }
    @MessageMapping("/chat")
    public void processMessage(ChatMessage message) {
        logger.info("📩 서버에서 받은 메시지: {}", message);

        try {
            // DB 저장 시도
            ChatMessage saved = chatService.saveMessage(message);
            logger.info("✅ 저장된 메시지: {}", saved);

            // 구독자에게 메시지 전송
            simpMessagingTemplate.convertAndSend("/topic/messages", saved);
        } catch (Exception e) {
            logger.error("❌ 메시지 저장 중 오류 발생!", e);
        }
    }


// ✅ 채팅 메시지 저장 (REST API)
    @PostMapping("/message")
    public ChatMessage saveMessage(@RequestBody ChatMessage message) {
        return chatService.saveMessage(message);
    }

    // ✅ 특정 채팅방의 메시지 조회 (REST API)
    @GetMapping("/messages/{roomName}")
    public List<ChatMessage> getMessages(@PathVariable String roomName) {
        return chatService.getMessagesByRoom(roomName);
    }
}
