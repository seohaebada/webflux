package com.example2_03.reactor.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class p152_FluxConcatExample {
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

        // 순서 보장
        // 각각 publisher의 onNext 이벤트 전파
        Flux.concat(flux1, flux2)
                .doOnNext(value -> {
                    log.info("doOnNext: " + value);
                })
                .subscribe();
        Thread.sleep(1000);
        log.info("end main");
    }
}
