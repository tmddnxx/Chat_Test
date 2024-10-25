package com.example.chat_test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/chat")
    private String getChat(){
        return "chat";
    }

    @MessageMapping("/chat")
    @SendTo("/sub/messages")
    public String sendMessage(@Payload String message) {
        log.info("받은 메시지: {}", message); // {} = 매개변수 포맷팅
        return message; // 클라이언트에 메시지를 다시 브로드캐스팅
    }
}
