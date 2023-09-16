package com.example06.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p186_FluxErrorExample {
    public static void main(String[] args) {
        log.info("start main");
        getItems().subscribe(new p181_SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Flux<Integer> getItems() {
        return Flux.create(fluxSink -> {
            fluxSink.next(0);
            fluxSink.next(1);
            var error = new RuntimeException("error in flux");
            fluxSink.error(error); // 에러 전달
        });
    }
}
