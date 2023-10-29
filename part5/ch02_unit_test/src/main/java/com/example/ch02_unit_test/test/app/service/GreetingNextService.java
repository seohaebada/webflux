package com.example.ch02_unit_test.test.app.service;

import com.example.ch02_unit_test.test.app.repository.greeting.GreetingR2dbcRepository;
import com.example.ch02_unit_test.test.app.repository.greeting.GreetingEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class GreetingNextService {
    private final GreetingR2dbcRepository greetingRepository;

    public Mono<String> greetingByWho(String who) {
        return greetingRepository.findByWho(who)
                .map(GreetingEntity::getGreeting)
                .switchIfEmpty(Mono.just("hello"))
                .map(greeting -> greeting + " " + who);
    }
}