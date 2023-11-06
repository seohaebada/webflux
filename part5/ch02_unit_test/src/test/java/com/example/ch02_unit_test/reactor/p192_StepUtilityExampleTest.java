package com.example.ch02_unit_test.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
public class p192_StepUtilityExampleTest {
    @Test
    void test1() {
        var flux = Flux.range(0, 10);

        // 5개만 요청
        StepVerifier.create(flux, 5)
                .expectSubscription()
                .expectNextCount(5) // 5개 잡히겠지 5개 요청했으니까
                .as("five elements") // description
                .then(() -> log.info("five elements")) // log
                .thenRequest(5) // 5개 추가로 요청
                .expectNextCount(5) // 5개 잡힘
                .verifyComplete(); // complete
    }
}
