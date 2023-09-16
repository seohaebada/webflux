package com.example04.completablefuture.completionstage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenComposeAsyncExample {
    public static void main(String[] args)
            throws InterruptedException {
        log.info("start main");
        CompletionStage<Integer> stage = p099_Helper.completionStage();
        stage.thenComposeAsync(value -> {
            var next = p099_Helper.addOne(value);
            log.info("in thenComposeAsync: {}", next);
            return next;
        }).thenComposeAsync(value -> {
            var next = p099_Helper.addResultPrefix(value);
            log.info("in thenComposeAsync2: {}", next);
            return next;
        }).thenAcceptAsync(value -> {
            log.info("{} in thenAcceptAsync", value);
        });

        Thread.sleep(1000);
        log.info("end main");
    }
}
