package com.example2_04.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

@Slf4j
public class p201_HttpHandlerExample {
    public static void main(String[] args) throws InterruptedException {
        log.info("start main");
        var httpHandler = new HttpHandler() {
            @Override
            public Mono<Void> handle(
                    ServerHttpRequest request,
                    ServerHttpResponse response) {
                // name 에 접근
                String nameQuery = request.getQueryParams().getFirst("name");
                String name = nameQuery == null ? "world" : nameQuery;

                String content = "Hello " + name;
                log.info("responseBody: {}", content);

                // body 담은 DataBuffer를 생성해서 Mono로 Wrapping
                Mono<DataBuffer> responseBody = Mono.just(
                        response.bufferFactory()
                                .wrap(content.getBytes())
                );

                // header 값 변경, cookie에 name 추가
                response.addCookie(ResponseCookie.from("name", name).build());
                response.getHeaders().add("Content-Type", "text/plain");

                // Mono로 wrapping한 DataBuffer 전달
                return response.writeWith(responseBody);
            }
        };

        var adapter = new ReactorHttpHandlerAdapter(httpHandler);
        // reactor netty server create
        // 요청 : http://localhost:8080/?name=taewoo
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();
    }
}
