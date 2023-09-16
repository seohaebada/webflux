package com.example05.reactive_streams;

import lombok.SneakyThrows;

import java.util.concurrent.Flow;

public class FixedIntPublisherExample {
    @SneakyThrows
    public static void main(String[] args) {
        Flow.Publisher publisher = new p166_FixedIntPublisher();
        Flow.Subscriber subscriber = new p167_RequestNSubscriber<>(Integer.MAX_VALUE);
        publisher.subscribe(subscriber);

        Thread.sleep(100);
    }
}