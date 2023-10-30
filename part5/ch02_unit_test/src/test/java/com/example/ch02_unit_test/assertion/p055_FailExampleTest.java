package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.TestToFail;

import static org.junit.jupiter.api.Assertions.fail;

public class p055_FailExampleTest {
    @TestToFail
    void test1() {
        var hasProblem = true;
        if (hasProblem) {
            fail();
        }
    }

    @TestToFail
    void test2() {
        var cause = new IllegalStateException();
        fail(cause);
    }
}
