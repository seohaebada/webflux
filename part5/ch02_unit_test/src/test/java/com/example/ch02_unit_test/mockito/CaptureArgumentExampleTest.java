package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

public class CaptureArgumentExampleTest {
    @Test
    void captureArgument() {
        GreetingService mocked = mock(GreetingService.class);

        mocked.greeting("world");
        mocked.greeting("grizz");
        mocked.greeting("earth");

        var captor = ArgumentCaptor.forClass(String.class);
        verify(mocked, times(3)).greeting(captor.capture());

        var expected = List.of("world", "grizz", "earth");
        assertIterableEquals(expected, captor.getAllValues());
    }
}
