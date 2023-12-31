package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.TestToFail;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class p082_AssertTimeoutExampleTest {
    @Test
    void test1() {
        var duration = Duration.ofSeconds(1);
        assertTimeout(duration, () -> {
            Thread.sleep(500);
        });
    }

    @Test
    void test2() {
        var duration = Duration.ofSeconds(1);
        Integer result = assertTimeout(duration, () -> {
            Thread.sleep(500);
            return 1;
        });
        assertEquals(1, result);
    }

    @TestToFail
    void test3() {
        var duration = Duration.ofMillis(500);
        assertTimeout(duration, () -> {
            Thread.sleep(1000);
        });
    }
}
