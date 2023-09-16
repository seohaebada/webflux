package com.example04.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class p086_FutureGetTimeoutExample {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException, TimeoutException {
        Future future = p083_FutureHelper.getFutureCompleteAfter1s();
        // get() ; 비동기 처리가 어려움
        var result = future.get(1500, TimeUnit.MILLISECONDS);
        assert result.equals(1);

        Future futureToTimeout = p083_FutureHelper.getFutureCompleteAfter1s();
        Exception exception = null;
        try {
            futureToTimeout.get(500, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            exception = e;
        }
        assert exception != null;
    }
}
