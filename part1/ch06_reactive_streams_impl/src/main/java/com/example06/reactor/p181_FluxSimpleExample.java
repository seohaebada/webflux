package com.example06.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class p181_FluxSimpleExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        // main 쓰레드에서 수행
        getItems() // 고정된 개수를 subscribe
                .subscribe(new p181_SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");

        Thread.sleep(1000);
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1, 2, 3, 4, 5));
    }
}
