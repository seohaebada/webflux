package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.test.Greeting;
import com.example.ch02_unit_test.test.GreetingWithEquals;
import com.example.ch02_unit_test.TestToFail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class p066_AssertInstanceOfExampleTest {
    @Test
    void test1() {
        Object obj = new Greeting("hello");
        assertInstanceOf(Greeting.class, obj);
    }

    @Test
    void test2() {
        Object obj = new GreetingWithEquals("hello");
        assertInstanceOf(Greeting.class, obj);
    }

    @TestToFail
    void test3() {
        Object obj = new Greeting("hello");
        assertInstanceOf(GreetingWithEquals.class, obj);
    }
}
