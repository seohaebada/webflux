package com.example.ch07_websocket.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class p358_EchoWebSocketHandler implements WebSocketHandler {
    @Override
    public List<String> getSubProtocols() {
        return WebSocketHandler.super.getSubProtocols();
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> echoFlux = session.receive() // 계속 받아오는 메시지
                .map(socketMessage -> {
                    String message = socketMessage.getPayloadAsText();
                    log.info("message: {}", message);
                    return session.textMessage("ECHO -> " + message);
                });

        return session.send(echoFlux); // 클라이언트로 전달
    }
}
