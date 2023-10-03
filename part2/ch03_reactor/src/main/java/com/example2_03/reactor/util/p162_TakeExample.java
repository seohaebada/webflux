package com.example2_03.reactor.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p162_TakeExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.range(1, 10)
                .take(5) // n개에 도달하면 onComplete 이벤트 발생
                .doOnNext(value -> {
                    log.info("take: " + value);
                })
                .subscribe();

        Flux.range(1, 10)
                // 1 2 3 4 5
                // 2 3 4 5 6
                // 3 4 5 6 7
                // 4 5 6 7 8
                // 5 6 7 8 9
                // 6 7 8 9 10 (여기서 onComplete 이벤트 발생)
                .takeLast(5) // onComplete 이벤트가 발생하기 직전 n개의 아이템만 전파 (마지막 5개 데이터만)
                .doOnNext(value -> {
                    log.info("takeLast: " + value);
                })
                .subscribe();

        Thread.sleep(1000);
        log.info("end main");

    }
}
