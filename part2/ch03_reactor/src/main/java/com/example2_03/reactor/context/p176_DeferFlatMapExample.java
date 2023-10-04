package com.example2_03.reactor.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class p176_DeferFlatMapExample {
    public static void main(String[] args) {
        log.info("start main");
        Mono.just(1)
                // 중간연산자에서 context에 접근하기 전 알아야할것

                // Mono.defer(() -> Mono.just(v))
                // = Mono.just(v)

                // flatMap(v -> Mono.defer(() -> { return Mono.just(v); }
                // flatMap(v -> Mono.just(v)))
                // = map(v -> v)
                .flatMap(v -> Mono.defer(() -> { // Supplier를 실행해서 Mono를 구하고, Mono로 이벤트를 전달
                    return Mono.just(v);
                })).subscribe(n -> {
                    log.info("next: {}", n);
                });
        log.info("end main");
    }
}
