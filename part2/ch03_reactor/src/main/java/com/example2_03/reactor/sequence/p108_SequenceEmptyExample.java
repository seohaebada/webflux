package com.example2_03.reactor.sequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class p108_SequenceEmptyExample {
    public static void main(String[] args) {
        log.info("start main");
        Mono.empty() // onComplete 이벤트만 전달
                .subscribe(value -> {
                    log.info("value: " + value);
                }, null, () -> {  // 세번째인자 : errorConsumer null 가능
                    log.info("complete");
                });
        Flux.empty()
                .subscribe(value -> {
                    log.info("value: " + value);
                }, null, () -> {
                    log.info("complete");
                });
        log.info("end main");
    }
}
