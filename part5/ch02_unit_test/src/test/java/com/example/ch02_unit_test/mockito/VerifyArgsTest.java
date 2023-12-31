package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class VerifyArgsTest {
    @Test
    void test1() {
        GreetingService mocked = mock(GreetingService.class);

        doReturn("hoi world").when(mocked)
                .greeting("world");

        mocked.hello("world");

        verify(mocked).hello("world");
        verify(mocked).hello(eq("world"));
        verify(mocked).hello(argThat(s -> {
            return s.equals("world");
        }));
        verify(mocked).hello(contains("world"));
    }
}
