package com.chat.services;

import java.util.List;
import com.chat.models.ChatRoom;
import com.chat.models.User;
import com.chat.models.dto.MessageDto;


public interface ChatService { 

    public void sendMessageToUsers( User sender, String roomName,List<String> usernames,String text);
    
    public ChatRoom findOrCreateChatRoom(String roomName, User sender, List<User> recipients);

    public List<MessageDto> getChatMessages(User currentUser,String roomName);
}
