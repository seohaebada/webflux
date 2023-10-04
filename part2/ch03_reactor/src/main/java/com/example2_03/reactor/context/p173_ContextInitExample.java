package com.example2_03.reactor.context;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

@Slf4j
public class p173_ContextInitExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        // 초깃값 설정
        var initialContext = Context.of("name", "taewoo");

        Flux.just(1)
                .flatMap(v -> ContextLogger.logContext(v, "1"))
                .contextWrite(context ->
                        context.put("name", "wooman"))
                .flatMap(v -> ContextLogger.logContext(v, "2"))
                // 4번째 인자로 초기값 전달 가능
                .subscribe(null, null, null, initialContext);
        log.info("end main");
    }
}
