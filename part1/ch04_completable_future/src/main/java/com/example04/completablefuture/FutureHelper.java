package com.example04.completablefuture;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureHelper {
    /**
     * 새로운 스레드를 생성하여 1을 반환
     * @return
     */
    public static Future<Integer> getFuture() {
        var executor = Executors.newSingleThreadExecutor();

        try {
            return executor.submit(() -> {
                return 1;
            });
        } finally {
            executor.shutdown();
        }
    }

    /**
     * 새로운 쓰레드를 생성하고 1초 대기 후 1을 반환
     * @return
     */
    public static Future<Integer> getFutureCompleteAfter1s() {
        var executor = Executors.newSingleThreadExecutor();

        try {
            return executor.submit(() -> {
                Thread.sleep(1000);
                return 1;
            });
        } finally {
            executor.shutdown();
        }
    }

    public static Future<Integer> getFutureWithException() {
        var executor = Executors.newSingleThreadExecutor();

        try {
            return executor.submit(() -> {
                throw new RuntimeException("Error");
            });
        } finally {
            executor.shutdown();
        }
    }
}
