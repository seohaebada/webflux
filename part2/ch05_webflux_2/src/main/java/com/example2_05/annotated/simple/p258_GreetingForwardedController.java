package com.example2_05.annotated.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/forwarded")
public class p258_GreetingForwardedController {
    @GetMapping
    void greet(ServerHttpRequest request) {
        log.info("uri: {}", request.getURI().toString());
    }
}
