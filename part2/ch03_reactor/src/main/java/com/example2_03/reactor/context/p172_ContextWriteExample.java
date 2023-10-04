package com.example2_03.reactor.context;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p172_ContextWriteExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        Flux.just(1)
                .flatMap(v -> ContextLogger.logContext(v, "1"))
                .contextWrite(context -> // 위로 전파함
                        context.put("name", "wooman"))
                .flatMap(v -> ContextLogger.logContext(v, "2"))
                .contextWrite(context -> // 위로 전파함
                        context.put("name", "taewoo"))
                .flatMap(v -> ContextLogger.logContext(v, "3"))
                .subscribe(); // 위로 전파함
        log.info("end main");
    }
}
