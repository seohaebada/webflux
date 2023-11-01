package com.example.ch02_unit_test.mockito;

import com.example.ch02_unit_test.test.app.service.GreetingService;
import com.grizz.wooman.test.TestToFail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;

public class CreateSpyExampleTest {
    static class Foo {
        Foo(String name) {}

        void bar() {}
    }

    @TestToFail
    void failToCreateSpy() {
        Foo a = spy(Foo.class);
        a.bar();
    }

    @Test
    void createSpy() {
        GreetingService spy = spy(GreetingService.class);
        assertNotNull(spy);
    }

    @Test
    void createSpyByObj() {
        GreetingService obj = new GreetingService();
        GreetingService spy = spy(obj);
        assertNotNull(spy);
    }
}
