package com.example.ch02_unit_test.assume;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class p086_AssumeTrueExampleTest {
    @Test
    void test1() {
        assumeTrue(false);
    }
}
