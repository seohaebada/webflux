package com.example2_03.reactor.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class p160_FlatMapExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.range(1, 5)
                // publihser의 이벤트를 아래로 전달
                // 여러 publisher를 조합해야하는 경우 유용
                .flatMap(value -> {
                    return Flux.range(1, 2)
                            .map(value2 -> value + ", " + value2)
                            .publishOn(Schedulers.parallel());
                })
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribe();
        Thread.sleep(1000);
        log.info("end main");

    }
}
