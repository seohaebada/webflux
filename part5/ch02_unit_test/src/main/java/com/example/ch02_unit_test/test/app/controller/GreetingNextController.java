package com.example.ch02_unit_test.test.app.controller;

import com.example.ch02_unit_test.test.app.service.GreetingNextService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/greeting")
public class GreetingNextController {
    private final GreetingNextService greetingService;

    @GetMapping("/diff")
    public Mono<String> diffGreetingByWho(
            @RequestParam String who
    ) {
        return greetingService.greetingByWho(who);
    }
}
