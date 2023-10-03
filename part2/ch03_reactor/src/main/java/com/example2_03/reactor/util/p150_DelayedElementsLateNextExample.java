package com.example2_03.reactor.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class p150_DelayedElementsLateNextExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.create(
                sink -> {
                    for (int i = 1; i <= 5; i++) {
                        try {
                            Thread.sleep(1000); // 1초
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        sink.next(i);
                    }
                    sink.complete();
                })
                // 위 1초 sleep 과 비교해서 더 늦게도착하면 바로 내보냄
                .delayElements(Duration.ofMillis(500))
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribeOn(Schedulers.single())
                .subscribe();
        Thread.sleep(6000);
        log.info("end main");
    }
}
