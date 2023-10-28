package com.example.ch07_websocket._실습.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ChatService {
    // 각자 고유의 sink
    private static Map<String, Sinks.Many<Chat>> chatSinkMap
            = new ConcurrentHashMap<>();

    public Flux<Chat> register(String iam) {
        // 연결이 되면, 본인의 sink에 등록
        Sinks.Many<Chat> sink = Sinks.many().unicast().onBackpressureBuffer();

        chatSinkMap.put(iam, sink);

        return sink.asFlux();
    }

    public boolean sendChat(String iam, Chat chat) {
        log.info("iam: {}, chat: {}", iam, chat);

        // 대상 찾기
        Sinks.Many<Chat> sink = chatSinkMap.get(iam);

        // 대상이 없을 경우
        if (sink == null) return false;

        // 대상에게 값을 흘려줌
        sink.tryEmitNext(chat);

        return true;
    }
}
