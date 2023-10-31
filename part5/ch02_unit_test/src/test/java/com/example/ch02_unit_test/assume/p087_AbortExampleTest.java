package com.example.ch02_unit_test.assume;

import com.example.ch02_unit_test.TestToIgnore;

import static org.junit.jupiter.api.Assumptions.abort;

public class p087_AbortExampleTest {
    @TestToIgnore
    void test1() {
        var hasProblem = true;
        if (hasProblem) {
            abort();
        }
    }
}
