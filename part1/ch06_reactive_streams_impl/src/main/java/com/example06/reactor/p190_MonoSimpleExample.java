package com.example06.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class p190_MonoSimpleExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        getItems()
                .subscribe(new p181_SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");

        Thread.sleep(1000);
    }

    private static Mono<Integer> getItems() {
        return Mono.create(monoSink -> {
            monoSink.success(1);
        });
    }
}
