package com.example2_03.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p140_OnErrorReturnAfterExecuteExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.just(1)
                // 고정된 값을 넘기기 위해 함수를 실행하면 문제 발생
                // 파이프라인을 미리 만드는 과정에서 에러가 발생하지 않더라도 무조건 함수를 실행한 후 값을 사용한다.
                .onErrorReturn(shouldDoOnError())
                .subscribe(value -> {
                    log.info("value: " + value);
                });
        log.info("end main");
    }

    private static int shouldDoOnError() {
        log.info("shouldDoOnError");
        return 0;
    }
}
