package com.example2_03.reactor.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p163_SkipExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.range(1, 10)
                .skip(5) // 처음 n개 이벤트 무시
                .doOnNext(value -> {
                    log.info("not skipped: " + value);
                })
                .subscribe();

        Flux.range(1, 10)
                .skipLast(5) // onComplete 발생 직전의 n개 이벤트 무시
                .doOnNext(value -> {
                    log.info("not skipped: " + value);
                })
                .subscribe();
        
        Thread.sleep(1000);
        log.info("end main");
    }
}
