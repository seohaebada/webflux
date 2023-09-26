package com.example2_03.reactor.sequence;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Slf4j
public class p114_SequenceCreateExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        // 아래 2갱츼 쓰레드에서 sink.next 수행

        // FluxSink
        Flux.create(new Consumer<FluxSink<Object>>() {
            @Override
            public void accept(FluxSink<Object> sink) {
                var task1 = CompletableFuture.runAsync(() -> {
                    for (int i = 0; i < 5; i++) {
                        log.info("task1: " + i);
                        sink.next(i);
                    }
                });

                var task2 = CompletableFuture.runAsync(() -> {
                    for (int i = 5; i < 10; i++) {
                        log.info("task2: " + i);
                        sink.next(i);
                    }
                });

                // 한쪽이 먼저 끝나면 next 전달이 안될 수 있으므로 두개가 모두 끝나고 complete 수행을 보장하기 위한 코드
                CompletableFuture.allOf(task1, task2)
                        .thenRun(sink::complete);
            }
        }).subscribe(value -> {
            log.info("value: " + value);
        }, error -> {
            log.error("error: " + error);
        }, () -> {
            log.info("complete");
        });

        Thread.sleep(1000);
    }
}
