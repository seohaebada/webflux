package com.example06.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class p185_ContinuousRequestSubscriber<T>
        implements Subscriber<T> {
    private final Integer count = 1;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        log.info("subscribe");
        s.request(count); // 개수만큼 요청
        log.info("request: {}", count);
    }

    @SneakyThrows
    @Override
    public void onNext(T t) {
        log.info("item: {}", t);

        Thread.sleep(1000);
        // 1개를 또 호출
        subscription.request(1);
        log.info("request: {}", count);
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
