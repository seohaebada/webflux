package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StubbingAnswerExampleTest {
    @Test
    void test1() {
        GreetingService mocked = mock(GreetingService.class);

        when(mocked.greeting(anyString()))
                .thenAnswer(invocation -> {
                    String name = invocation.getArgument(0);
                    if (name.equals("grizz")) {
                        throw new ArithmeticException();
                    }
                    return "hoi " + name;
                });

        assertEquals("hoi world", mocked.greeting("world"));
        assertThrows(ArithmeticException.class, () -> {
            mocked.greeting("grizz");
        });
    }
}