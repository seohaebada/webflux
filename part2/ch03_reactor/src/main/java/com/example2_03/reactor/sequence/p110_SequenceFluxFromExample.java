package com.example2_03.reactor.sequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class p110_SequenceFluxFromExample {
    public static void main(String[] args) {
        log.info("start main");

        // Iterable
        Flux.fromIterable(
                List.of(1, 2, 3, 4, 5)
        ).subscribe(value -> {
            log.info("value: " + value);
        });

        // Stream
        Flux.fromStream(
                IntStream.range(1, 6).boxed()
        ).subscribe(value -> {
            log.info("value: " + value);
        });

        // Array
        Flux.fromArray(
                new Integer[]{1, 2, 3, 4, 5}
        ).subscribe(value -> {
            log.info("value: " + value);
        });

        // start부터 1개씩 커진 값을 n개만큼
        Flux.range(
                1, 5
        ).subscribe(value -> {
            log.info("value: " + value);
        });
        log.info("end main");
    }
}
