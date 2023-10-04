package com.example2_03.reactor.context;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p169_UselessThreadLocalExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        // main
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("wooman");

        Flux.create(sink -> { // single
            // 접근 불가능
            log.info("threadLocal: " + threadLocal.get());
            sink.next(1);
        }).publishOn(Schedulers.parallel()
        ).map(value -> { // parallel
            // 접근 불가능
            log.info("threadLocal: " + threadLocal.get());
            return value;
        }).publishOn(Schedulers.boundedElastic()
        ).map(value -> { // boundedElastic
            // 접근 불가능
            log.info("threadLocal: " + threadLocal.get());
            return value;
        }).subscribeOn(Schedulers.single()
        ).subscribe();

        Thread.sleep(1000);
        log.info("end main");
    }
}
