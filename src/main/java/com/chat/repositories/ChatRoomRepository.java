package com.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chat.models.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByName(String name);

}
