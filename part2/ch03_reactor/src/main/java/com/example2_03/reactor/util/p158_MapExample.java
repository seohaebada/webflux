package com.example2_03.reactor.util;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p158_MapExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.range(1, 5)
                .map(value -> value * 2)
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribe();

        Flux.range(1, 5)
                .mapNotNull(value -> {
                    if (value % 2 == 0) {
                        return value;
                    }
                    return null; // 변경된 값이 null인 경우 필터
                })
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribe();
        log.info("end main");
    }
}
