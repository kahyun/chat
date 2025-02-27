package com.example.chat.controller;

import com.example.chat.dto.RoomCreateRequest;
import com.example.chat.entity.ChatRoom;
import com.example.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public ChatRoom createRoom(@RequestBody RoomCreateRequest roomCreateRequest) {
        return chatRoomService.createRoom(roomCreateRequest.getRoomName(), roomCreateRequest.getId());

    }
    @GetMapping("/member/{id}")
    public List<ChatRoom> getRoomForId(@PathVariable String id) {
        return chatRoomService.getRoomsForId(id);
    }

    @PostMapping("/{roomId}/members")
    public ChatRoom addMemberToRoom(@PathVariable String roomId, @RequestParam String Id) {
        return chatRoomService.addMember(roomId, Id);
    }
}
