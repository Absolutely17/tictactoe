package com.absolutely.tictactoe.controller;

import com.absolutely.tictactoe.chat.SendMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    @MessageMapping("/message")
    public void getMessages(SendMessage request){
        this.simpMessagingTemplate.convertAndSend("/chat/message/" + request.getIdChat(), request.getMessage());
    }
}