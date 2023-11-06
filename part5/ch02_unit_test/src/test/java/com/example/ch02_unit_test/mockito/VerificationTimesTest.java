package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import com.example.ch02_unit_test.TestToFail;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class VerificationTimesTest {
    @Test
    void test1() {
        GreetingService mocked = mock(GreetingService.class);

        mocked.hello("world");
        mocked.hello("world");
        mocked.hello("world");

        verify(mocked, never()).hello("grizz");
        verify(mocked, times(3)).hello("world");
        verify(mocked, atLeast(3)).hello("world");
        verify(mocked, atLeast(0)).hello("world");
        verify(mocked, atMost(3)).hello("world");
        verify(mocked, atMost(99999)).hello("world");
    }

    @TestToFail
    void test2() {
        GreetingService mocked = mock(GreetingService.class);

        mocked.hello("world");
        mocked.hello("world");
        mocked.hello("world");

        verify(mocked, times(100)).hello("world");
        verify(mocked, atLeast(4)).hello("world");
        verify(mocked, atMost(2)).hello("world");
    }
}
