package com.chat.services.impl;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.chat.models.ChatRoom;
import com.chat.models.Message;
import com.chat.models.User;
import com.chat.models.dto.MessageDto;
import com.chat.repositories.ChatRoomRepository;
import com.chat.repositories.MessageRepository;
import com.chat.repositories.UserRepository;
import com.chat.services.ChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;


      public void sendMessageToUsers( User sender, String roomName,List<String> usernames,String text) {
        List<User> recipients = userRepository.findByUsernameIn(usernames);

        ChatRoom chatRoom = findOrCreateChatRoom(roomName, sender, recipients);

        Message message = new Message();
        message.setText(text);
        message.setSendDate(LocalDateTime.now());
        message.setSender(sender);
        message.setChatRoom(chatRoom);
        messageRepository.save(message);
    }
    public ChatRoom findOrCreateChatRoom(String roomName, User sender, List<User> recipients) {

        Set<User> participants = new HashSet<>(recipients);
        participants.add(sender);

        if (roomName != null) {
            ChatRoom existingChatRoom = chatRoomRepository.findByName(roomName);
            if (existingChatRoom != null) {
                if (!participants.equals(existingChatRoom.getParticipants())) {
                    throw new RuntimeException("Existing room has different participants");
                }
                return existingChatRoom;
            }
        }
    
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setName(roomName != null ? roomName : "Default Chat Room");
        newChatRoom.getParticipants().addAll(participants);
        return chatRoomRepository.save(newChatRoom);
    }

    public List<MessageDto> getChatMessages(User currentUser,String roomName) {
        
        ChatRoom chatRoom = chatRoomRepository.findByName(roomName);

        if (chatRoom == null) {
            throw new RuntimeException("Chat room not found: " + roomName);
        }
        if (!chatRoom.getParticipants().contains(currentUser)) {
            throw new RuntimeException("You are not a participant in this chat room: " + roomName);
        }
    
        List<Message> messages = messageRepository.findByChatRoomOrderBySendDate(chatRoom);

        List<MessageDto> chatMessages = messages.stream()
                .map(message -> new MessageDto(
                        message.getSender().getUsername(), 
                        message.getSendDate(),             
                        message.getText()                  
                ))
                .collect(Collectors.toList());

        return chatMessages;
    }
}
