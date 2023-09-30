package com.example2_03.reactor.error;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Slf4j
public class p141_OnErrorResumeExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.error(new RuntimeException("error"))
                // error가 발생한 경우에만 apply 실행
                .onErrorResume(new Function<Throwable, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(Throwable throwable) {
                        return Flux.just(0, -1, -2);
                    }
                })
                .subscribe(value -> {
                    log.info("value: " + value);
                });
        log.info("end main");
    }
}
