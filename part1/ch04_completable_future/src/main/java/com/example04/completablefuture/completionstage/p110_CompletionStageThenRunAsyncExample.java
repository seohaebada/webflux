package com.example04.completablefuture.completionstage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class p110_CompletionStageThenRunAsyncExample {
    public static void main(String[] args)
            throws InterruptedException {
        log.info("start main");
        CompletionStage<Integer> stage = p099_Helper.completionStage();
        stage.thenRunAsync(() -> {
            log.info("in thenRunAsync");
        }).thenRunAsync(() -> {
            log.info("in thenRunAsync2");
        }).thenAcceptAsync(value -> {
            log.info("{} in thenAcceptAsync", value);
        });

        Thread.sleep(100);
    }
}
