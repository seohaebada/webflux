package com.example04.completablefuture.completionstage;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionStage;

@Slf4j
public class CompletionStageThenApplyExample {
    public static void main(String[] args) {
        CompletionStage<Integer> stage = p099_Helper.completionStageAfter1s();
        stage.thenApply(i -> {
            log.info("in thenApply");
            return i + 1;
        }).thenAccept(value -> log.info("{}", value));

        CompletionStage<Integer> stage2 = p099_Helper.completionStageAfter1s();
        stage2.thenApplyAsync(i -> {
            log.info("in thenApplyAsync");
            return i + 1;
        }).thenAccept(value -> log.info("{}", value));
    }
}
