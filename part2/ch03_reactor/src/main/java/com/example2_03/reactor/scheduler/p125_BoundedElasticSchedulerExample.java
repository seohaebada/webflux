package com.example2_03.reactor.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p125_BoundedElasticSchedulerExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        for(int i = 0; i < 200; i++) {
            final int idx = i;
            Flux.create(sink -> {
                log.info("next: {}", idx);
                sink.next(idx);
            }).subscribeOn(
                    /*
                    캐싱된 고정되지 않은 크기의 쓰레드풀 제공
                    재사용할 수 있는 쓰레드가 있다면 사용하고, 없으면 새로 생성
                    특정시간(기본 60초) 사용하지 않으면 제거
                    생성 가능한 쓰레드 수 제한 (기본 cpu x 10)
                    I/O blocking 작업을 수행할때 적합
                     */
                    Schedulers.boundedElastic()
            ).subscribe(value -> {
                log.info("value: " + value);
            });
        }
        Thread.sleep(1000);
        log.info("end main");
    }
}
