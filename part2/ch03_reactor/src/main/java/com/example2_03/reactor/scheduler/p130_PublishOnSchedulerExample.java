package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p130_PublishOnSchedulerExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.create(sink -> { // main
            for (var i = 0; i < 5; i++) {
                log.info("next: {}", i);
                sink.next(i);
            }
        }).publishOn(
                // 이후에 추가되는 연산자들의 실행 쓰레드에 영향을 준다.
                Schedulers.single()
        ).doOnNext(item -> { // single
            log.info("doOnNext: {}", item);
        }).publishOn(
                Schedulers.boundedElastic()
        ).doOnNext(item -> { // boundedElastic
            log.info("doOnNext2: {}", item);
        }).subscribe(value -> { // boundedElastic
            log.info("value: " + value);
        });
        Thread.sleep(1000);
        log.info("end main");
    }
}
