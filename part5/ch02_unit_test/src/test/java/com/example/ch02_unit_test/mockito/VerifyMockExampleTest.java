package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class VerifyMockExampleTest {
    @Test
    void verifyMock() {
        GreetingService mocked = mock(GreetingService.class);

        mocked.hello("world");

        verify(mocked).hello(argThat(s -> s.equals("world")));
    }
}
