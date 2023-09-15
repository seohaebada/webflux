package com.example04.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureAmbiguousStateExample {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        Future futureToCancel = FutureHelper.getFuture();
        futureToCancel.cancel(true);
        assert futureToCancel.isDone();

        Future futureWithException = FutureHelper.getFutureWithException();

        Exception exception = null;
        try {
            futureWithException.get();
        } catch (ExecutionException e) {
            exception = e;
        }

        // 완료된건지, 에러가 발생한건지 구분하기 어렵다.
        assert futureWithException.isDone();
        assert exception != null;
    }
}
