package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p123_ParallelSchedulerExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            Flux.create(sink -> {
                log.info("next: {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    // 캐싱된 n개 만큼의 쓰레드풀을 제공
                    // 기본적으로 CPU 코어 수만큼의 크기를 갖는다.
                    Schedulers.parallel()
            ).subscribe(value -> {
                log.info("value: " + value);
            });
        }
        Thread.sleep(2000);
        log.info("end main");
    }
}
