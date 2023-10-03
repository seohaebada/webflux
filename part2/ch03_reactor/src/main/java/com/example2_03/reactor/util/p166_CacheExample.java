package com.example2_03.reactor.util;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p166_CacheExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux<Object> flux = Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                log.info("next: " + i);
                sink.next(i); // 0, 1, 2
            }
            log.info("complete in publisher"); // 한번만 출력되겠다. cache() 때문에
            sink.complete();
        }).cache(); // 이게 없다면 아래 계속 위 create를 실행했을것

        flux.subscribe(value -> {
            log.info("value: " + value);
        }, null, () -> {
            log.info("complete");
        });

        flux.subscribe(value -> {
            log.info("value: " + value);
        }, null, () -> {
            log.info("complete");
        });

        log.info("end main");
    }
}
