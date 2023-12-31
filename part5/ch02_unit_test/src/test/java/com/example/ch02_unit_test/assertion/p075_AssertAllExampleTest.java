package com.example.ch02_unit_test.assertion;

import com.example.ch02_unit_test.TestToFail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;

public class p075_AssertAllExampleTest {
    @Test
    void test1() {
        assertAll(
                () -> {
                },
                () -> {
                },
                () -> {
                }
        );
    }

    @TestToFail
    void test2() {
        Stream<Executable> executables = Stream.of(
                () -> {
                },
                () -> {
                    throw new IllegalStateException();
                }
        );
        assertAll(executables);
    }
}
