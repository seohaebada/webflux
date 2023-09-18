package com.exmaple2_02.netty.eventloop;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class p038_EventLoopNonIOTaskExample {
    public static void main(String[] args) {
        log.info("start main");
        // eventloop 가 별도의 쓰레드풀을 갖는다.
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);

        // main 쓰레드
        for (int i = 0; i < 10; i++) {
            final int idx = i;
            // 실행하는 쓰레드와 eventLoopGroup 쓰레드가 서로 다르므로 execute 할때마다 taskqueue에 차례대로 들어간다.
            eventLoopGroup.execute(() -> {
                log.info("i: {}", idx);
            });
        }

        eventLoopGroup.shutdownGracefully();
        log.info("end main");
    }
}
