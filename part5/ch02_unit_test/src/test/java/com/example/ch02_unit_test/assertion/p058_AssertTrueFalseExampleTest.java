package com.example.ch02_unit_test.assertion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class p058_AssertTrueFalseExampleTest {
    @Test
    void checkTrue() {
        assertTrue(true);

    }

    @Test
    void checkTrueWithSupplier() {
        assertTrue(() -> {
            return true;
        });
    }

    @Test
    void checkFalse() {
        assertFalse(false);
    }
}
