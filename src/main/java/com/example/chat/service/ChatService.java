package com.example.chat.service;

import com.example.chat.entity.ChatMessage;
import com.example.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ChatService.class);
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage saveMessage(ChatMessage message) {
        logger.info("💾 DB 저장 시도: {}");

        try {
            ChatMessage saved = chatMessageRepository.save(message);
            logger.info("✅ DB 저장 완료: {}");
            return saved;
        } catch (Exception e) {
            logger.error("❌ DB 저장 실패!", e);
            return null;
        }
    }

    public List<ChatMessage> getMessagesByRoom(String roomName) {
        return chatMessageRepository.findByRoomName(roomName);
    }
}
