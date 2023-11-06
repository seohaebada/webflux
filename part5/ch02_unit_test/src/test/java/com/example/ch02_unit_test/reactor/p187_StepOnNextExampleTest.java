package com.example.ch02_unit_test.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class p187_StepOnNextExampleTest {
    @Test
    void test1() {
        var flux = Flux.range(0, 10);

        StepVerifier.create(flux)
                .assertNext(i -> {
                    assertEquals(0, i);
                })
                .expectNext(1, 2) // 1, 2
                .expectNextCount(3) // 3, 4, 5
                .expectNextSequence(List.of(6, 7, 8)) // 6, 7, 8
                .expectNextMatches(i -> i == 9) // 9
                .expectComplete()
                .verify();
    }
}
