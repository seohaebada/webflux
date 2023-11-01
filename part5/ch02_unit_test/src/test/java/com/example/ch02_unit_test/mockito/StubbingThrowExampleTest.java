package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubbingThrowExampleTest {
    @Test
    void test1() {
        GreetingService mocked = mock(GreetingService.class);

        when(mocked.greeting("world"))
                .thenThrow(IllegalStateException.class);

        assertThrows(IllegalStateException.class, () -> {
            mocked.greeting("world");
        });
    }

    @Test
    void test2() {
        GreetingService mocked = mock(GreetingService.class);
        when(mocked.greeting("world"))
                .thenThrow(
                        new IllegalStateException(),
                        new IllegalArgumentException(),
                        new ArithmeticException()
                );

        assertThrows(IllegalStateException.class, () -> {
            mocked.greeting("world");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mocked.greeting("world");
        });
        assertThrows(ArithmeticException.class, () -> {
            mocked.greeting("world");
        });
    }
}
