package com.project.ChatApplication.WebSocket;




import com.project.ChatApplication.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventListener {
    Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener

    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String username = "User"; // Extract username logic here
        log.info("User Disconnected : " + username);
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setType(ChatMessage.MessageType.LEAVE);
        chatMessage.setSender(username);
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
