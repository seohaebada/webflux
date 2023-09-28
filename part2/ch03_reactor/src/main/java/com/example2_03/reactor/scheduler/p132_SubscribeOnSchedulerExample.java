package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p132_SubscribeOnSchedulerExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.create(sink -> {
            for (var i = 0; i < 5; i++) { // boundedEalastic (subscribe 실행 쓰레드 영향)
                log.info("next: {}", i);
                sink.next(i);
            }
        }).doOnNext(item -> { // // boundedEalastic
            log.info("doOnNext: {}", item);
        }).doOnNext(item -> { // // boundedEalastic
            log.info("doOnNext2: {}", item);
        }).subscribeOn(Schedulers.boundedElastic()
        ).subscribe(value -> { // boundedEalastic
            log.info("value: " + value);
        });
        Thread.sleep(1000);
        log.info("end main");
    }
}
