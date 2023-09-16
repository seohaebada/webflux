package com.example04.completablefuture.completionstage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class p092_CompletionStageExample {
    public static void main(String[] args) throws InterruptedException {
        // chaining
        p099_Helper.completionStage()
                .thenApplyAsync(value -> {
                    log.info("thenApplyAsync: {}", value);
                    return value + 1;
                }).thenAccept(value -> {
                    log.info("thenAccept: {}", value);
                }).thenRunAsync(() -> {
                    log.info("thenRun");
                }).exceptionally(e -> {
                    log.info("exceptionally: {}", e.getMessage());
                    return null;
                });

        Thread.sleep(100);
    }
}
