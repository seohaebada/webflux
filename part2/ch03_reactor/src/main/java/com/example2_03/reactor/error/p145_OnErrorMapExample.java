package com.example2_03.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
public class p145_OnErrorMapExample {
    private static class CustomBusinessException
            extends RuntimeException {
        public CustomBusinessException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        log.info("start main");
        Flux.error(new IOException("fail to read file"))
                // onError 이벤트를 처리하기 위해 다른 이벤트로 변환
                // 변환만 하므로 에러 핸들링은 필요
                .onErrorMap(e -> new CustomBusinessException("custom"))
                .subscribe(value -> {
                            log.info("value: " + value);
                        }, e -> {
                            log.info("error: " + e);
                        }
                );
        log.info("end main");
    }
}
