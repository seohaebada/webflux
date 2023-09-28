package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Slf4j
public class p127_ExecutorServiceSchedulerExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            Flux.create(sink -> {
                log.info("next: {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    // 직접 주입
                    Schedulers.fromExecutorService(executorService)
            ).subscribe(value -> {
                log.info("value: " + value);
            });
        }
        Thread.sleep(1000);
        executorService.shutdown();
        log.info("end main");
    }
}
