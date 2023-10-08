package com.example2_04.webflux;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.nio.charset.StandardCharsets;

@Slf4j
public class p227_WebHandlerOnlyMultipartDataFileExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var objectMapper = new ObjectMapper();

        var webHandler = new WebHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange) {
                final ServerHttpResponse response = exchange.getResponse();

                return exchange.getMultipartData().flatMap(multipartData -> {
                    return (multipartData.getFirst("data")).content()
                            // DataBuffer -> String
                            .map(dataBuffer -> dataBuffer.toString(StandardCharsets.UTF_8))
                            // Mono를 반환하기 때문에 flatMap을 사용해서 다음 String으로 넘긴다.
                            .reduce((s1, s2) -> s1 + s2);// 계속 합쳐서 하나의 문자열로 변환
                }).flatMap(json -> { // String
                    String name;
                    try {
                        name = objectMapper.readTree(json).get("name").asText();
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage());
                        name = "world";
                    }

                    String content = "Hello " + name;
                    log.info("responseBody: {}", content);
                    Mono<DataBuffer> responseBody = Mono.just(
                            response.bufferFactory()
                                    .wrap(content.getBytes())
                    );

                    response.addCookie(
                            ResponseCookie.from("name", name).build());
                    response.getHeaders()
                            .add("Content-Type", "text/plain");
                    return response.writeWith(responseBody);
                });
            }
        };

        final HttpHandler webHttpHandler = WebHttpHandlerBuilder
                .webHandler(webHandler)
                .build();

        final var adapter = new ReactorHttpHandlerAdapter(webHttpHandler);
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();
    }
}
