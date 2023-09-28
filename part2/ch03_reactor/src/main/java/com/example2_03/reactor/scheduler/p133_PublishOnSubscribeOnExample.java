package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p133_PublishOnSubscribeOnExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.create(sink -> { // parallel
            for (var i = 0; i < 5; i++) {
                log.info("next: {}", i);
                sink.next(i);
            }
        }).publishOn(
                Schedulers.single()
        ).doOnNext(item -> { // single
            log.info("doOnNext: {}", item);
        }).publishOn(
                Schedulers.boundedElastic()
        ).doOnNext(item -> { // boundedElastic
            log.info("doOnNext2: {}", item);
        }).subscribeOn(Schedulers.parallel()
        ).subscribe(value -> { // parallel
            log.info("value: " + value);
        });
        Thread.sleep(1000);
        log.info("end main");
    }
}
