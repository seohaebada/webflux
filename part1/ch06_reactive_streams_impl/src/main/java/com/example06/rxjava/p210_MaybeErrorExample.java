package com.example06.rxjava;

import io.reactivex.rxjava3.core.Maybe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class p210_MaybeErrorExample {
    public static void main(String[] args) {
        maybeGetItem()
                .subscribe(new SimpleMaybeObserver<>());
    }

    private static Maybe<Integer> maybeGetItem() {
        return Maybe.create(maybeEmitter -> {
            var error = new RuntimeException("Error in maybe");
            maybeEmitter.onError(error);
        });
    }
}
