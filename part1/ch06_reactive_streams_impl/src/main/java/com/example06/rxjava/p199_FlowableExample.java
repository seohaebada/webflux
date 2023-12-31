package com.example06.rxjava;

import com.example06.reactor.p181_SimpleSubscriber;
import io.reactivex.rxjava3.core.Flowable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class p199_FlowableExample {
    public static void main(String[] args) {
        log.info("start main");
        getItems()
                .subscribe(new p181_SimpleSubscriber<>(Integer.MAX_VALUE));
        log.info("end main");
    }

    private static Flowable<Integer> getItems() {
        return Flowable.fromIterable(List.of(1, 2, 3, 4, 5));
    }
}
