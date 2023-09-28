package com.example2_03.reactor.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Slf4j
public class p119_SingleThreadRunExmample {
    public static void main(String[] args) {
        log.info("start main");
        var executor = Executors.newSingleThreadExecutor();
        try {
            // publisher는 아무런 설정을 하지 않으면 subscribe를 호출한 caller의 쓰레드에서 실행
            executor.submit(() -> {
                Flux.create(sink -> {
                    for (int i = 1; i <= 5; i++) {
                        log.info("next: {}", i);
                        sink.next(i);
                    }
                }).subscribeOn( // subscribe를 해당 shceduler로 진행하겠다는 의미
                        Schedulers.single()
                ).subscribe(value -> {
                    log.info("value: " + value);
                });
            });
        } finally {
            executor.shutdown();
        }


    }
}
