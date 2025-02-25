package com.example.chat.entity;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@AllArgsConstructor
@Document(collection = "messages")  // ✅ MongoDB 컬렉션
public class ChatMessage {

    @Id
    private String id;  // ✅ MongoDB는 String 타입 ID를 주로 사용

    private String name;
    private String roomName;
    private String content;
    private LocalDateTime createdAt;
    private Boolean read;

    }

