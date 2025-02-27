package com.example.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rooms")
public class ChatRoom {
    @Id
    private String roomId;

    private String roomName;
    private List<String> id;
    private LocalDateTime createdAt;

}
