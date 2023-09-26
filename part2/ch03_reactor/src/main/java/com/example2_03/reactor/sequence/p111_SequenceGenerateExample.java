package com.example2_03.reactor.sequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p111_SequenceGenerateExample {
    public static void main(String[] args) {
        log.info("start main");
        // 동기적 Flux 생성
        Flux.generate(
                () -> 0, // Callable<S> stateSupplier
                (state, sink) -> { // BiFunction<S, SynchronousSink<T>, S> generator
                    // 한번의 generator에서 최대 한번만 next 호출 가능
                    sink.next(state); // 두번째 인자로 next, error, complete 호출 가능
                    if (state == 9) {
                        sink.complete();
                    }
                    return state + 1; // 변경된 state 반환
                }
        ).subscribe(value -> {
            log.info("value: " + value);
        }, error -> {
            log.error("error: " + error);
        }, () -> {
            log.info("complete");
        });
    }
}
