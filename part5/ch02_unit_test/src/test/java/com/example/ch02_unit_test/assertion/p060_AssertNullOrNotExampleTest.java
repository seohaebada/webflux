package com.example.ch02_unit_test.assertion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class p060_AssertNullOrNotExampleTest {
    Object target;

    @Test
    void checkNull() {
        assertNull(target);
    }

    @Nested
    class WhenTargetIsNotNull {
        @BeforeEach
        void setup() {
            target = 1;
        }

        @Test
        void checkNotNull() {
            assertNotNull(target);
        }
    }
}
