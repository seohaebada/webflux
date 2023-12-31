package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Slf4j
public class ExecuteMockExampleTest {
    @Test
    void mockMethods() {
        GreetingService mocked = mock(GreetingService.class);

        // do nothing
        mocked.hello("world");

        var actualCount = mocked.greetingCount();
        assertEquals(0, actualCount);

        var actualGreeting = mocked.greeting("world");
        assertNull(actualGreeting);

        var actualMono = mocked.greetingMono("world");
        assertNull(actualMono);
    }
}
