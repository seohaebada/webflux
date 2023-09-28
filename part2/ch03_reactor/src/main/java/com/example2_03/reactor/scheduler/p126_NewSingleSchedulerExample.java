package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p126_NewSingleSchedulerExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        for (int i = 0; i < 100; i++) {
            // 캐싱이 아닌 새로운 쓰레드풀을 만들어서 제공
            var newSingle = Schedulers.newSingle("single");
            final int idx = i;
            Flux.create(sink -> {
                log.info("next: {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    newSingle
            ).subscribe(value -> {
                log.info("value: " + value);
                newSingle.dispose(); // 쓰레드풀 해제
            });
        }
        Thread.sleep(1000);
        log.info("end main");
    }
}
