package com.example2_03.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class p137_ErrorNoHandleExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.create(sink -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 에러 핸들링이 없는 경우 기본적으로 onErrorDropped가 호출됨
            sink.error(new RuntimeException("error"));
        }).subscribe();
        log.info("end main");
    }
}
