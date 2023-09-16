package com.example04.completablefuture.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class p120_CompletableFutureCompleteExample {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        assert !future.isDone();

        // 완료되지 않았다면 주어진 값으로 채운다.
        var triggered = future.complete(1);
        assert future.isDone();
        assert triggered;
        assert future.get() == 1;

        triggered = future.complete(2);
        assert future.isDone();
        assert !triggered;
        assert future.get() == 1;
    }
}
