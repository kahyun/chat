package com.example.chat.service;

import com.example.chat.entity.ChatRoom;
import com.example.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    //채팅방 생성 ( 원본 내용은 보존 )
    public ChatRoom createRoom(String roomName, List<String> name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(roomName);
        chatRoom.setName(name);
        chatRoom.setCreatedAt(LocalDateTime.now());
        ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
        return savedRoom;
    }

    public List<ChatRoom> getRoomsForName(String name) {
        return chatRoomRepository.findByNameContaining(name);
    }

    public ChatRoom addMember(String roomId, String newMemberName) {
        Optional<ChatRoom> optionalRoom = chatRoomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            ChatRoom room = optionalRoom.get();
            List<String> memberList = room.getName();
            if (!memberList.contains(newMemberName)) {
                memberList.add(newMemberName);
                room.setName(memberList);
                return chatRoomRepository.save(room);
            }
            return room;
        } else {
            throw new RuntimeException("Room not found with id: " + roomId);
        }
    }
}
