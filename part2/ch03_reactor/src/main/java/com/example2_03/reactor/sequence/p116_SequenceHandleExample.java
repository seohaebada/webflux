package com.example2_03.reactor.sequence;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

@Slf4j
public class p116_SequenceHandleExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        Flux.fromStream(IntStream.range(0, 10).boxed())
                // BiConsumer
                .handle((value, sink) -> { // item, SynchronousSink
                    sink.complete();
                    if (value % 2 == 0) { // 필터
                        sink.next(value); // 주어진 아이템을 전달할지 말지 결정
                    }
                }).subscribe(value -> {
                    log.info("value: " + value);
                }, error -> {
                    log.error("error: " + error);
                }, () -> {
                    log.info("complete");
                });
        log.info("end main");
    }
}
