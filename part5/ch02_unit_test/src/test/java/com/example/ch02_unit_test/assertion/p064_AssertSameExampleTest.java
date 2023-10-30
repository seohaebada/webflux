package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.test.Greeting;
import com.example.ch02_unit_test.test.GreetingWithEquals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class p064_AssertSameExampleTest {
    @Test
    void test1() {
        var expected = new GreetingWithEquals("hello");
        var actual = new GreetingWithEquals("hello");
        assertNotSame(expected, actual);
    }

    @Test
    void test2() {
        Greeting expected = new Greeting("hello");
        Greeting actual = expected;
        assertSame(expected, actual);
    }
}
