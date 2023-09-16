package com.example06.reactor;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
@RequiredArgsConstructor
public class p181_SimpleSubscriber<T> implements Subscriber<T> {
    private final Integer count;

    /**
     * 지속적으로 요청을 하는게 아니라, 딱 한번 N개의 요청을 받고 그 이후로 값을 계속 받음
     * @param s the {@link Subscription} that allows requesting data via {@link Subscription#request(long)}
     */
    @Override
    public void onSubscribe(Subscription s) {
        log.info("subscribe");
        s.request(count); // count만큼 request
        log.info("request: {}", count);
    }

    @SneakyThrows
    @Override
    public void onNext(T t) {
        log.info("item: {}", t);
        Thread.sleep(100);
    }

    @Override
    public void onError(Throwable t) {
        log.error("error: {}", t.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("complete");
    }
}
