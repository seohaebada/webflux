package com.example2_03.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p143_OnErrorCompleteExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.create(sink -> {
                    sink.next(1);
                    sink.next(2);
                    sink.error(new RuntimeException("error"));
                }).onErrorComplete() // error을 삼켜서 complete 이벤트로 변경
                .subscribe(value -> {
                            log.info("value: " + value);
                        }, e -> {
                            log.info("error: " + e);
                        }, () -> {
                            log.info("complete");
                        }
                );
        log.info("end main");
    }
}
