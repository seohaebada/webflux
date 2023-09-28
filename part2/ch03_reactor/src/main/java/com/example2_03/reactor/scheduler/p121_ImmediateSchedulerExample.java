package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p121_ImmediateSchedulerExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.create(sink -> {
            for (int j = 1; j <= 5; j++) {
                log.info("next: {}", j);
                sink.next(j);
            }
        }).subscribeOn( // subscribe를 호출한 caller 쓰레드에서 즉시 실행
                Schedulers.immediate()
        ).subscribe(value -> {
            log.info("value: " + value);
        });
        log.info("end main");
        Thread.sleep(1000);
    }
}
