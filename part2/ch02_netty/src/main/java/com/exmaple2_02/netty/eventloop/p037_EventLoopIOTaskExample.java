package com.exmaple2_02.netty.eventloop;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class p037_EventLoopIOTaskExample {
    public static void main(String[] args) {
        log.info("start main");
        // netty 전용으로 제공되는 서버 소켓 채널
        var channel = new NioServerSocketChannel();
        // 1개의 evnetloop 생성과 동일한 상황
        // 내부에 몇개의 eventloop를 포함할지 설정
        var eventLoopGroup = new NioEventLoopGroup(1);
        // accept network I/O 이벤트를 eventLoop에 등록
        // 여러개의 채널을 등록 가능
        eventLoopGroup.register(channel);

        channel.bind(new InetSocketAddress(8080))
                .addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("Server bound to port 8080");
                    } else {
                        log.info("Failed to bind to port 8080");
                        eventLoopGroup.shutdownGracefully();
                    }
                });
        log.info("end main");
    }
}
