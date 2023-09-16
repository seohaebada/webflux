package com.example06.rxjava;

import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class p205_SingleExample {
    public static void main(String[] args) {
        getItem()
                .subscribe(new p205_SimpleSingleObserver<>());
    }

    private static Single<Integer> getItem() {
        return Single.create(singleEmitter -> {
            singleEmitter.onSuccess(1);
        });
    }
}
