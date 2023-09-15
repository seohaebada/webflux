package com.example03.asyncprogramming;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class AsyncBlockingExample {
    public static void main(String[] args) {
        log.info("Start main");
        // main은 getResult의 결과에 관심이 없다.
        // getResult 결과를 이용해서 함수형 인터페이스를 실행한다.
        getResult(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                var nextValue = integer + 1;
                assert nextValue == 1;
            }
        });
        log.info("Finish main");
    }

    // ac
    public static void getResult(
            Consumer<Integer> callback
    ) {
        log.info("Start getResult");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var result = 0;
        try {
            callback.accept(result);
        } finally {
            log.info("Finish getResult");
        }
    }
}
