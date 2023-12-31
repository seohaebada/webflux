package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.TestToFail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class p080_AssertDoesNotThrowExampleTest {
    @Test
    void test1() {
        assertDoesNotThrow(
                () -> {}
        );
    }

    @Test
    void test2() {
        Integer result = assertDoesNotThrow(
                () -> { return 1; }
        );
        assertEquals(1, result);
    }

    @TestToFail
    void test3() {
        assertDoesNotThrow(
                () -> { throw new IllegalStateException(); }
        );
    }
}
