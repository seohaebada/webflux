package com.example06.rxjava;

import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class p206_SingleNullExample {
    public static void main(String[] args) {
        getItem()
                .subscribe(new p205_SimpleSingleObserver<>());
    }

    private static Single<Integer> getItem() {
        return Single.create(singleEmitter -> {
            singleEmitter.onSuccess(null); // 에러 발생시킴
        });
    }
}
