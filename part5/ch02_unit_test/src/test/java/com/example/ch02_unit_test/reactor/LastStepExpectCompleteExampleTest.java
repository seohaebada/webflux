package com.example.ch02_unit_test.reactor;

import com.example.ch02_unit_test.TestToFail;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class LastStepExpectCompleteExampleTest {
    @Test
    void test1() {
        var flux = Flux.range(0, 5);

        StepVerifier.create(flux)
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }

    @TestToFail
    void test2() {
        var flux = Flux.error(new IllegalStateException());

        StepVerifier.create(flux)
                .expectComplete()
                .verify();
    }
}
