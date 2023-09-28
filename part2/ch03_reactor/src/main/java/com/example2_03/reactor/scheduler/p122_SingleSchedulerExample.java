package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p122_SingleSchedulerExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            Flux.create(sink -> {
                log.info("next: {}", idx);
                sink.next(idx);
            }).subscribeOn( // 캐싱된 1개크기의 쓰레드풀을 제공 (동시에 여러개에서 호출하더라도 딱 1개의 scheduler를 계속 제공)
                    Schedulers.single()
            ).subscribe(value -> {
                log.info("value: " + value);
            });
        }
        Thread.sleep(1000);
        log.info("end main");
    }
}
