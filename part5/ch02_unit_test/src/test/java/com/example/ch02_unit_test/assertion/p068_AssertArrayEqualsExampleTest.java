package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.TestToFail;
import com.example.ch02_unit_test.test.Greeting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class p068_AssertArrayEqualsExampleTest {
    @Test
    void test1() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }

    @TestToFail
    void test2() {
        Object[] expected = {new Greeting("hello")};
        Object[] actual = {new Greeting("hello")};
        assertArrayEquals(expected, actual);
    }
}
