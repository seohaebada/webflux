package com.example.ch02_unit_test.condition;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOnMacExampleTest {
    @TestOnMac
    void test1() {
        assertTrue(true);
    }
}
