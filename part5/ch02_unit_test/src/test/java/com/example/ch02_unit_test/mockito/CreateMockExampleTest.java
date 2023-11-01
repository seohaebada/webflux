package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

@Slf4j
public class CreateMockExampleTest {
    @Test
    void createMock() {
        GreetingService mocked = mock(GreetingService.class);

        assertInstanceOf(GreetingService.class, mocked);
    }

    @Test
    void createMock2() {
        var mocked = mock(GreetingService.class);

        assertInstanceOf(GreetingService.class, mocked);
    }
}
