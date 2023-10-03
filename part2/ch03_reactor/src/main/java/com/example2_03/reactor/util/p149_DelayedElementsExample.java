package com.example2_03.reactor.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class p149_DelayedElementsExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.create(
                sink -> {
                    for (int i = 1; i <= 5; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        sink.next(i);
                    }
                    sink.complete();
                })
                // delay만큼의 간격을 두고 onNext 이벤트를 발행
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
