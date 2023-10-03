package com.chat.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.models.ChatRoom;
import com.chat.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
     List<Message> findByChatRoomOrderBySendDate(ChatRoom chatRoom);
}