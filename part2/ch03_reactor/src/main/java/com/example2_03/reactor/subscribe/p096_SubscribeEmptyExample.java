package com.example2_03.reactor.subscribe;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class p096_SubscribeEmptyExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .doOnNext(value -> { // 파이프라인에 영향을 주지 않고 지나가는 값 확인
                    log.info("value: " + value);
                })
                .subscribe();
        log.info("end main");
    }
}
