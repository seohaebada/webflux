package com.ch06_sse.sse.controller;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RequestMapping("/sse")
@Controller
public class SseController {
    @ResponseBody
    @GetMapping(path = "/simple", produces = "text/event-stream")
    Flux<String> simpleSse() {
        return Flux.interval(Duration.ofMillis(100)) // 100ms 간격으로 하나씩 증가된 값을 무한히 만드는 Flux
                .map(i -> "Hello " + i);
    }

    @ResponseBody
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ServerSentEvent<String>> sse(
            @RequestHeader(name = "Last-Event-ID",
                    required = false, defaultValue = "0") Long lastEventId
    ) {
        return Flux.range(0, 5)
                .delayElements(Duration.ofMillis(100))
                        .map(i -> ServerSentEvent.<String>builder() // id, event, retry, comment, data 설정 가능
                                .event("add")
                                .id(String.valueOf(i + lastEventId + 1))
                                .data("data-" + i)
                                .comment("comment-" + i)
                                .build());

    }
}
