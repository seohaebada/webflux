package com.example.ch02_unit_test.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class p051_NonNestedExampleTest {
    @Test
    void test1() {
        log.info("test1");
    }

    // @Nested 가 없으면 실행안됨
    class Nested1 {
        @Test
        void test2() {
            log.info("test2");
        }
    }
}
