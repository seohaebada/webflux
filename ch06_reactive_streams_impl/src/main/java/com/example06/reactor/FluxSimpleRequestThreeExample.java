package com.example06.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class FluxSimpleRequestThreeExample {
    public static void main(String[] args) {
        // 3개 요청 (1, 2, 3 이후 종료) , 추가적인 요청 없음 
        getItems().subscribe(new SimpleSubscriber<>(3));
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1, 2, 3, 4, 5));
    }
}
