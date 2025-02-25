package com.example.chat.repository;

import com.example.chat.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomName(String roomName);  // 특정 채팅방의 메시지 가져오기
}
