package com.example06.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class p195_ListMonoToFluxExample {
    public static void main(String[] args) {
        log.info("start main");
        getItems()
                // Mono의 결과를 Flux 형태로 바꾸고, flux를 받아서 처리
                // 1, 2, 3, 4, 5 하나씩 처리 
                .flatMapMany(value -> Flux.fromIterable(value))
                .subscribe(new p181_SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Mono<List<Integer>> getItems() {
        return Mono.just(List.of(1, 2, 3, 4, 5));
    }
}
