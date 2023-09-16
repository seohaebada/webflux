package com.example06.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class p192_FluxToMonoExample {
    public static void main(String[] args) {
        log.info("start main");
        // 1,2,3,4,5 중 첫번째값 1이 onNext로 전달되고 complete
        // 뒤에 있는 값들은 모두 무시
        Mono.from(getItems())
                .subscribe(new p181_SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1, 2, 3, 4, 5));
    }
}
