package com.example2_03.reactor.context;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

@Slf4j
public class p174_ContextReadFromSinkExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var initialContext = Context.of("name", "taewoo");

        Flux.create(sink -> {
            // source에 sink가 있다면 sink.contextView로 접근
            var name = sink.contextView().get("name");
            log.info("name in create: " + name);
            sink.next(1);
        }).contextWrite(context ->
                context.put("name", "taewoo")
        ).subscribe(null, null, null, initialContext);

        Thread.sleep(1000);
        log.info("end main");
    }
}
