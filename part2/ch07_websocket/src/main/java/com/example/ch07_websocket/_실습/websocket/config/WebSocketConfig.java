package com.example.ch07_websocket._실습.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class WebSocketConfig {
    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter(webSocketService());
    }

    /**
     * Webfliter와 비슷한 역할
     * @return
     */
    @Bean
    public WebSocketService webSocketService() {
        // HandshakeWebSocketService 구현
        // handleRequest 메서드 오버라이딩
        HandshakeWebSocketService webSocketService = new HandshakeWebSocketService() {
            @Override
            public Mono<Void> handleRequest(ServerWebExchange exchange, WebSocketHandler handler) {
                String iam = exchange.getRequest()
                        .getHeaders()
                        .getFirst("X-I-AM"); // header 로부터 값 얻음

                // 존재하지 않는다면 더이상 진행할 수 없게끔 complete 처리
                if (iam == null) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                // session에 iam 넣기
                return exchange.getSession()
                        .flatMap(session -> {
                            session.getAttributes().put("iam", iam);
                            return super.handleRequest(exchange, handler);
                        });
            }
        };

        webSocketService.setSessionAttributePredicate(s -> true);
        return webSocketService;
    }
}
