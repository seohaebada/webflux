package com.example.ch02_unit_test.common;

import com.example.ch02_unit_test.test.OddNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class p050_OddNumberValidatorTest {
    Integer number;
    OddNumberValidator oddNumberValidator;

    @BeforeEach
    void beforeEach() {
        oddNumberValidator = new OddNumberValidator();
    }

    @Nested
    class OddNumber {
        @BeforeEach
        void beforeEach() { number = 1; }

        @Test
        void test() {
            assertTrue(oddNumberValidator.isValid(number));
        }
    }

    @Nested
    class EvenNumber {
        @BeforeEach
        void beforeEach() { number = 2; }

        @Test
        void test() {
            assertFalse(oddNumberValidator.isValid(number));
        }
    }
}
