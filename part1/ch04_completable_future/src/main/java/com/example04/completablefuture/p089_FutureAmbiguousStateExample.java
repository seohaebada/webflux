package com.example04.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class p089_FutureAmbiguousStateExample {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        Future futureToCancel = p083_FutureHelper.getFuture();
        futureToCancel.cancel(true);
        assert futureToCancel.isDone();

        Future futureWithException = p083_FutureHelper.getFutureWithException();

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
