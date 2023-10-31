package com.example.ch02_unit_test.assume;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class p089_AssumeTrueFalseExampleTest {
    @Test
    void checkTrue() {
        assumeTrue(true);
    }

    @Test
    void checkTrueWithSupplier() {
        assumeTrue(() -> {
            return true;
        });
    }

    @Test
    void checkFalse() {
        assumeFalse(false);
    }
}
