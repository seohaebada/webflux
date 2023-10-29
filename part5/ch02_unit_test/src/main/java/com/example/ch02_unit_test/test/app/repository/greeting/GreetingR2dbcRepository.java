package com.example.ch02_unit_test.test.app.repository.greeting;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface GreetingR2dbcRepository
        extends R2dbcRepository<GreetingEntity, Long> {
    Mono<GreetingEntity> findByWho(String who);
}
