package com.example06.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class MonoToFluxExample {
    public static void main(String[] args) {
        log.info("start main");

        // flux() - Mono를 next 한번 호출하고 onComplete를 호출하는 Flux로 변환
        // [1,2,3,4,5]
        getItems().flux()
                .subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Mono<List<Integer>> getItems() {
        return Mono.just(List.of(1, 2, 3, 4, 5));
    }
}
