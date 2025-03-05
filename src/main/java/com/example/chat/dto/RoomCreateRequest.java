package com.example.chat.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomCreateRequest {
    private String roomName;
    private List<String> name;
}
