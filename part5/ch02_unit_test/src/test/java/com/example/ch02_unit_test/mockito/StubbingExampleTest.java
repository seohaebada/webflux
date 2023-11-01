package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StubbingExampleTest {
    @Test
    void test1() {
        GreetingService mocked = mock(GreetingService.class);

        when(mocked.greetingCount())
                .thenReturn(10000);
        when(mocked.greeting("world"))
                .thenReturn("hoi world");
        when(mocked.greetingMono("world"))
                .thenReturn(Mono.just("hoi world"));

        assertEquals(10000, mocked.greetingCount());

        var actualGreeting = mocked.greeting("world");
        assertEquals("hello world", actualGreeting);

        actualGreeting = mocked.greetingMono("world").block();
        assertEquals("hello world", actualGreeting);
    }
}
