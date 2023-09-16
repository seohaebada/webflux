package com.example04.completablefuture.completionstage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class p102_CompletionStageThenAcceptAsyncRunningExample {
    public static void main(String[] args)
            throws InterruptedException {
        log.info("start main");

        // thread pool에 있는 쓰레드에서 action 실행
        CompletionStage<Integer> stage = p099_Helper.runningStage();
        stage.thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync", i);
        }).thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync", i);
        });

        Thread.sleep(2000);
    }
}
