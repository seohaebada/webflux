package com.example06.mutiny;

import io.smallrye.mutiny.Multi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiExample {
    public static void main(String[] args) {
        getItems()
                .subscribe()
                // subscribe 동시에 넘길 수 없음, subscribe() 호출 후 아래 호출 필요
                .withSubscriber(
                        new SimpleMultiSubscriber<>(Integer.MAX_VALUE)
                );
    }

    private static Multi<Integer> getItems() {
        return Multi.createFrom().items(1, 2, 3, 4, 5);
    }
}
