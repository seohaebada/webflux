package com.example2_03.reactor.subscribe;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class p097_SubscribeFunctionInterfaceExample {
    public static void main(String[] args) {
        log.info("start main");
        Flux.fromIterable(List.of(1, 2, 3, 4, 5))
                .subscribe(new Consumer<Integer>() { // consumer
                    @Override
                    public void accept(Integer integer) {
                        log.info("value: " + integer);
                    }
                }, new Consumer<Throwable>() { // errorConsumer
                    @Override
                    public void accept(Throwable throwable) {
                        log.error("error: " + throwable);
                    }
                }, new Runnable() { // completeConsumer
                    @Override
                    public void run() {
                        log.info("complete");
                    }
                }, Context.empty()); // initialContext (upstream에 전달할 context)
        log.info("end main");
    }
}
