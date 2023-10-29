package com.example.ch02_unit_test.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 구조화
 */
@Slf4j
public class p048_NestedExampleTest {
    @Test
    void test1() {
        log.info("test1");
    }

    @Nested
    class Nested1 {
        @Test
        void test2() {
            log.info("test2");
        }

        @Nested
        class Nested2 {
            @Test
            void test3() {
                log.info("test3");
            }
        }
    }
}
