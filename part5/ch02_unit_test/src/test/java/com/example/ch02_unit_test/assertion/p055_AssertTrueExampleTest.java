package com.example.ch02_unit_test.assertion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class p055_AssertTrueExampleTest {
    @Test
    void shouldFail() {
        assertTrue(false);
    }
}
