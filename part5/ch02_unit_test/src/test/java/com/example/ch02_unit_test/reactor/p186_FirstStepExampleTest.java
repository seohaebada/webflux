package com.example.ch02_unit_test.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class p186_FirstStepExampleTest {
    @Test
    void test1() {
        var flux = Flux.range(0, 5);

        var options = StepVerifierOptions.create()
                // 위에서 1000개의 아이템을 전달했다면, 100개 이후에도 request를 추가로 해줘야함
                .initialRequest(100)
                .withInitialContext(Context.empty())
                .scenarioName("test1");

        StepVerifier.create(flux, options)
                .expectSubscription()
                .expectNextCount(5) // next가 5번 일어나길 기대함
                .verifyComplete();
    }
}
