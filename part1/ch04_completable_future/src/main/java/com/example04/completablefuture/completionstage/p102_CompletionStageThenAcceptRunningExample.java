package com.example04.completablefuture.completionstage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class p102_CompletionStageThenAcceptRunningExample {
    public static void main(String[] args)
            throws InterruptedException {
        log.info("start main");

        // thenAccept가 호출된 callee 쓰레드에서 action 실행
        CompletionStage<Integer> stage = p099_Helper.runningStage();
        stage.thenAccept(i -> {
            log.info("{} in thenAccept", i);
        }).thenAccept(i -> {
            log.info("{} in thenAccept2", i);
        });

        Thread.sleep(2000);
    }
}
