package com.chat.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.chat.models.User;
import com.chat.models.dto.MessageDto;
import com.chat.services.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/chatrooms")
@RequiredArgsConstructor
public class ChatController {

    
    private final ChatService chatService;
    
    private static final String sendMessage = "/send";
    private static final String seeMessages = "/messages";

    @PostMapping(sendMessage)
    public void sendMessageToUsers(
            @AuthenticationPrincipal User sender,
            @RequestParam("roomName") String roomName,
            @RequestParam("usernames") List<String> usernames,
            @RequestParam("text") String text
    ) {
        chatService.sendMessageToUsers(sender,roomName, usernames,text);
    }
    
    @GetMapping(seeMessages)
    public List<MessageDto> getChatMessages(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("roomName") String roomName
    ) {
        return chatService.getChatMessages(currentUser,roomName);
    }
}
