package com.example.ch02_unit_test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.abort;

class p010_SimpleTestExamples {
    /**
     * failed
     */
    @Test
    void test1() {
        assertTrue(true);
    }

    /**
     * failed
     */
    @Test
    void test2() {
        fail("fail");
    }

    /**
     * ignored
     */
    @Test
    void test3() {
        abort("abort");
    }
}
