package com.example2_05.functional;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
public class p276_RouterFunctionExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        RouterFunction<ServerResponse> router = route() // builder 호출 메서드
                // builder의 path() (공통 path)
                .path("/greet", b1 -> b1
                        // 주어진 요청이 아래와 같은 MediaType할 수 있는가?
                        // 할 수 있다면, 아래쪽으로 넘긴다.
                        .nest(accept(MediaType.TEXT_PLAIN), b2 -> b2
                                .GET("/",
                                        queryParam("name", name -> !name.isBlank()),
                                        GreetingHandler::greetQueryParam)
                                .GET("/name/{name}", GreetingHandler::greetPathVariable)
                                .GET("/header",
                                        headers(h -> h.firstHeader("X-Custom-Name") != null),
                                        GreetingHandler::greetHeader)
                                .POST("/json", contentType(MediaType.APPLICATION_JSON),
                                        GreetingHandler::greetJsonBody)
                                .POST("/text", GreetingHandler::greetPlainTextBody)
                        )
                )
                .build();

        var httpHandler = RouterFunctions.toHttpHandler(router);
        var adapter = new ReactorHttpHandlerAdapter(httpHandler);

        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow()
                .channel().closeFuture().sync();
    }
}
