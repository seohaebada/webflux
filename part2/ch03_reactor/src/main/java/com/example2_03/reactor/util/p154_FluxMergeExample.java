package com.example2_03.reactor.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class p154_FluxMergeExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var flux1 = Flux.range(1, 3)
                .doOnSubscribe(value -> {
                    log.info("doOnSubscribe1");
                })
                .delayElements(Duration.ofMillis(100));
        var flux2 = Flux.range(10, 3)
                .doOnSubscribe(value -> {
                    log.info("doOnSubscribe2");
                })
                .delayElements(Duration.ofMillis(100));

        // 시작하자마자 모든 publisher를 바로 subscribe
        // 각각 Publisher의 onNext 이벤트가 동시에 도달
        // Publisher의 순서가 보장되지 않는다.
        Flux.merge(flux1, flux2)
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribe();
        Thread.sleep(1000);
        log.info("end main");
    }
}
