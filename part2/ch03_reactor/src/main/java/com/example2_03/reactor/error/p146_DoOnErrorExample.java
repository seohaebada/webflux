package com.example2_03.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p146_DoOnErrorExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.error(new RuntimeException("error"))
                // 파이프라인 프름에 영향을 주지 않고 error 로깅만 가능
                .doOnError(error -> {
                    log.info("doOnError: " + error);
                })
                .subscribe(value -> {
                    log.info("value: " + value);
                }, error -> {
                    log.info("error: " + error);
                });
        log.info("end main");
    }
}
