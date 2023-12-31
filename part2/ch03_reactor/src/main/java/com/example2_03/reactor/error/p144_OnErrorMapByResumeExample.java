package com.example2_03.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
public class p144_OnErrorMapByResumeExample {
    private static class CustomBusinessException
            extends RuntimeException {
        public CustomBusinessException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        log.info("start main");
        Flux.error(new IOException("fail to read file"))
                .onErrorResume(e ->
                        // error을 다른 error로 변환하여 전달
                        Flux.error(new CustomBusinessException("custom")))
                .subscribe(value -> {
                            log.info("value: " + value);
                        }, e -> {
                            log.info("error: " + e);
                        }
                );
        log.info("end main");
    }
}
