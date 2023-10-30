package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.TestToFail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class AssertThrowsExactlyExampleTest {
    @Test
    void test1() {
        assertThrowsExactly(
                IllegalStateException.class,
                () -> {
                    throw new IllegalStateException();
                }
        );
    }

    @TestToFail
    void test2() {
        assertThrowsExactly(
                RuntimeException.class,
                () -> {
                    throw new IllegalStateException();
                }
        );
    }
}
