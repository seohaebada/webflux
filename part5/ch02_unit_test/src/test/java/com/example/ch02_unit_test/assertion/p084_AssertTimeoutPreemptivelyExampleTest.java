package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.TestToFail;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class p084_AssertTimeoutPreemptivelyExampleTest {
    @TestToFail
    void test1() {
        var duration = Duration.ofMillis(500);
        assertTimeoutPreemptively(duration, () -> {
            Thread.sleep(1000);
        });
    }
}
