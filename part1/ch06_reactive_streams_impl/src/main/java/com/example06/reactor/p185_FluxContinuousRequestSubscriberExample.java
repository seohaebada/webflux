package com.example06.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class p185_FluxContinuousRequestSubscriberExample {
    public static void main(String[] args) {
        getItems().subscribe(new p185_ContinuousRequestSubscriber<>());
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1, 2, 3, 4, 5));
    }
}
