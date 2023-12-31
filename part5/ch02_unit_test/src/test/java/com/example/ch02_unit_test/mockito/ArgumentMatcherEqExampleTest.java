package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ArgumentMatcherEqExampleTest {
    @Test
    void test1() {
        GreetingService mocked = mock(GreetingService.class);

        when(mocked.greeting(eq("world")))
                .thenReturn("hoi world");

        assertEquals("hoi world", mocked.greeting("world"));
        verify(mocked).greeting(anyString());
    }
}
