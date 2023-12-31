package com.example04.completablefuture.completionstage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class p100_CompletionStageThenAcceptAsyncExample {
    public static void main(String[] args)
            throws InterruptedException {
        log.info("start main");

        // done 상태일때 thread pool에 있는 쓰레드에서 action 실행
        CompletionStage<Integer> stage = p099_Helper.finishedStage();
        stage.thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync", i);
        }).thenAcceptAsync(i -> {
            log.info("{} in thenAcceptAsync2", i);
        });
        log.info("after thenAccept");

        Thread.sleep(100);
    }
}
