package com.example2_03.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p139_OnErrorReturnExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.error(new RuntimeException("error"))
                .onErrorReturn(0) // 에러가 발생하면 고정된 값을 반환
                .subscribe(value -> {
                    log.info("value: " + value);
                });
        log.info("end main");
    }
}
