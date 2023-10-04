package com.example2_03.reactor.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class p175_DeferExample {
    public static void main(String[] args) {
        log.info("start main");
        Mono.defer(() -> { // Consumer를 인자로 받아서 publisher 생성하고 이 publisher의 이벤트를 아래로 전달
            return Mono.just(1);
        }).subscribe(n -> {
            log.info("next: {}", n);
        });
        log.info("end main");
    }
}
