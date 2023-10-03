package com.example2_03.reactor.util;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p159_DoOnXXExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.range(1, 5)
                .map(value -> value * 2)
                // 각각의 이벤트를 흐름에 영향을 주지않고,
                // 위에서 내려오는 이벤트에 대해서 로깅이나 추가 작업 가능
                // 아래 map 과는 관련X (위에서 내려오는것만)
                .doOnNext(value -> { // map을 지난 시점에 도달
                    log.info("doOnNext: " + value);
                })
                .doOnComplete(() -> {
                    log.info("doOnComplete");
                })
                .doOnSubscribe(subscription -> {
                    log.info("doOnSubscribe");
                })
                .doOnRequest(value -> {
                    log.info("doOnRequest: " + value);
                })
                .map(value -> value / 2)
                .subscribe();
        log.info("end main");
    }
}
