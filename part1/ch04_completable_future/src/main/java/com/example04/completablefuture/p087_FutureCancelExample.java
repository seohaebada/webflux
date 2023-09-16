package com.example04.completablefuture;

import java.util.concurrent.Future;

public class p087_FutureCancelExample {
    public static void main(String[] args) {
        Future future = p083_FutureHelper.getFuture();
        // 취소할 수 없는 상황이라면 false 리턴
        // mayInterruptIfRunning이 false 라면 시작하지 않은 작업에 대해서만 취소
        var successToCancel = future.cancel(true);
        assert future.isCancelled();
        assert future.isDone();
        assert successToCancel;

        successToCancel = future.cancel(true);
        assert future.isCancelled();
        assert future.isDone();
        assert !successToCancel;
    }
}
