package com.example06.rxjava;

import io.reactivex.rxjava3.core.Completable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class p212_CompletableExample {
    public static void main(String[] args) {
        getCompletion()
                .subscribe(new SimpleCompletableObserver());
    }

    private static Completable getCompletion() {
        return Completable.create(completableEmitter -> {
            Thread.sleep(1000);
            completableEmitter.onComplete(); // 값이 아닌 사건을 전달 
        });
    }
}
