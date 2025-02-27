package com.example.chat.service;

import com.example.chat.entity.ChatMessage;
import com.example.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final MongoTemplate mongoTemplate;

    public ChatMessage saveMessage(ChatMessage message) {
        try {
            message = new ChatMessage(
                    message.getId(),
                    message.getName(),
                    message.getRoomName(),
                    message.getContent(),
                    LocalDateTime.now(),
                    false,
                    false
            );
            ChatMessage saved = chatMessageRepository.save(message);
            return saved;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ChatMessage> getMessagesByRoom(String roomName) {
        return chatMessageRepository.findByRoomName(roomName);
    }
    public List<String> findDistinctRoomNames() {
        return mongoTemplate.query(ChatMessage.class)
                .distinct("roomName")
                .as(String.class)
                .all();
    }
    public ChatMessage deleteMessage(String messageId) {
        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("메시지를 찾을 수 없습니다:"+ messageId));
        message.setDeleted(true);
        return chatMessageRepository.save(message);
    }
}
