package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CallSpyExampleTest {
    @Test
    void test1() {
        GreetingService spy = spy(GreetingService.class);
        verify(spy, never()).greeting(anyString());

        var greeting = spy.greeting("world");
        assertEquals("hello world", greeting);
        verify(spy).greeting("world");

        when(spy.greeting("world"))
                .thenReturn("hoi world")
                .thenCallRealMethod()
                .thenThrow(ArithmeticException.class);

        greeting = spy.greeting("world");
        assertEquals("hoi world", greeting);
        verify(spy, times(2)).greeting("world");

        greeting = spy.greeting("world");
        assertEquals("hello world", greeting);
        verify(spy, times(3)).greeting("world");

        assertThrows(ArithmeticException.class, () -> {
            spy.greeting("world");
        });
    }
}
