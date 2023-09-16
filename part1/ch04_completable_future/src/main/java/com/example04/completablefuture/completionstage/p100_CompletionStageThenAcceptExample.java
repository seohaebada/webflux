package com.example04.completablefuture.completionstage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class p100_CompletionStageThenAcceptExample {
    public static void main(String[] args)
            throws InterruptedException {
        log.info("start main");

        // done 상태일때 thenAccept를 호출한 caller 쓰레드에서 action 실행
        CompletionStage<Integer> stage = p099_Helper.finishedStage();
        stage.thenAccept(i -> {
            log.info("{} in thenAccept", i);
        }).thenAccept(i -> {
            log.info("{} in thenAccept2", i);
        });
        log.info("after thenAccept");

        Thread.sleep(100);
    }
}
