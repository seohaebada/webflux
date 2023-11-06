package com.example.ch02_unit_test.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class p179_StepVerifierTest {
    @Test
    void test1() {
        Flux<Integer> flux = Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        });

        // 아래 test를 하겠다는 의미
        // verify() 호출 시점에 이벤트를 전달받으면서 테스트 실행
        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .expectComplete()
                .verify();

    }
}
