package com.exmaple2_02.netty.eventloop;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class p040_EventLoopGroupNonIOTaskExample {
    public static void main(String[] args) {
        log.info("start main");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(5);

        for (int i = 0; i < 12; i++) {
            final int idx = i;
            // 2개 이상의 eventloop Group이므로 순차적으로 돌면서 5개의 task queue에 하나씩 넣고, 처리
            eventLoopGroup.execute(() -> {
                log.info("i: {}", idx);
            });
        }

        eventLoopGroup.shutdownGracefully();
        log.info("end main");
    }
}
