package com.example04.completablefuture.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class p121_CompletableFutureIsCompletedExceptionallyExample {
    public static void main(String[] args)
            throws InterruptedException {
        var futureWithException = CompletableFuture.supplyAsync(() -> {
            return 1 / 0;
        });
        Thread.sleep(100);
        assert futureWithException.isDone();
        // exception에 의해서 complete 되었는가?
        assert futureWithException.isCompletedExceptionally();
    }
}
