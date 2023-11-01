package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StubbingVoidExampleTest {
    @Test
    void test1() {
        GreetingService mocked = mock(GreetingService.class);

        doThrow(ArithmeticException.class)
                .when(mocked)
                .hello("grizz");

        doNothing()
                .when(mocked)
                .hello("world");

        doReturn("hoi world")
                .when(mocked)
                .greeting("world");

        assertThrows(ArithmeticException.class, () -> {
            mocked.hello("grizz");
        });
        assertDoesNotThrow(() -> mocked.hello("world"));
        assertEquals("hoi world", mocked.greeting("world"));
    }
}
