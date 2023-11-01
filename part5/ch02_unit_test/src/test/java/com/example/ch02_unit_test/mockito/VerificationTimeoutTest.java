package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import com.grizz.wooman.test.TestToFail;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

public class VerificationTimeoutTest {
    @Test
    void test1() {
        GreetingService mocked = mock(GreetingService.class);

        mocked.hello("world");

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mocked.hello("world");
            mocked.hello("world");
            mocked.hello("world");
        });

        verify(mocked, times(1)).hello("world");
        var mode = timeout(1000).times(4);
        verify(mocked, mode).hello("world");
    }

    @TestToFail
    void test2() {
        GreetingService mocked = mock(GreetingService.class);

        var mode = timeout(1000).atLeastOnce();
        verify(mocked, mode).hello("world");
    }
}
