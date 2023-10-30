package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.test.Greeting;
import com.example.ch02_unit_test.TestToFail;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class p070_AssertIterableEqualsExampleTest {
    @Test
    void test1() {
        Iterable<Integer> expected = List.of(1, 2, 3);
        Iterable<Integer> actual = List.of(1, 2, 3);
        assertIterableEquals(expected, actual);
    }

    @TestToFail
    void test2() {
        Iterable<Object> expected =
                List.of(new Greeting("hello"));
        Iterable<Object> actual =
                List.of(new Greeting("hello"));
        assertIterableEquals(expected, actual);
    }
}
