package com.example06.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class FluxToListMonoExample {
    public static void main(String[] args) {
        log.info("start main");
        getItems()
                // collect 하고 complete 이벤트 발생 시점에 모은 값들을 모두 전달
                // 1, 2, 3, 4, 5를 내부 배열에 저장하고, 가지고있던 값들을 모두 onNext() 한다.
                // 하나로 합쳐져서 Mono로 한번 요청됨 ([1,2,3,4,5]
                .collectList()
                .subscribe(new SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Flux<Integer> getItems() {
        return Flux.fromIterable(List.of(1, 2, 3, 4, 5));
    }
}
