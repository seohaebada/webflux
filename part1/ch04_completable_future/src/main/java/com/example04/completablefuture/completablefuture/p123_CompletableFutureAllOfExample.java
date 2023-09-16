package com.example04.completablefuture.completablefuture;

import com.example04.completablefuture.completionstage.p099_Helper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class p123_CompletableFutureAllOfExample {
    public static void main(String[] args)
            throws InterruptedException {
        var startTime = System.currentTimeMillis();
        var firstFuture = p099_Helper.waitAndReturn(100, 1);
        var secondFuture = p099_Helper.waitAndReturn(500, 2);
        var thirdFuture = p099_Helper.waitAndReturn(1000, 3);

        CompletableFuture.allOf(firstFuture, secondFuture, thirdFuture)
                .thenAcceptAsync(v -> {
                    log.info("after allOf");
                    try {
                        log.info("first: {}", firstFuture.get());
                        log.info("second: {}", secondFuture.get());
                        log.info("third: {}", thirdFuture.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).join();

        var endTime = System.currentTimeMillis();
        log.info("elapsed: {}ms", endTime - startTime);
    }
}
