package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.test.Greeting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class p062_AssertEqualsOrNotExampleTest {
    @Test
    void checkEquals() {
        assertEquals(1, 1);
    }

    @Test
    void checkNotEquals() {
        assertNotEquals(1, 2);
    }

    @Test
    void checkObjectEquals() {
        Assertions.assertNotEquals(
                new Greeting("hello"), new Greeting("hello")
        );
    }
}
