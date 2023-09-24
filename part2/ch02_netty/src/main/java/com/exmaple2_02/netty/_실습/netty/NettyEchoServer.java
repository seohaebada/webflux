package com.exmaple2_02.netty._실습.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyEchoServer {
    @SneakyThrows
    public static void main(String[] args) {
        // serverSocket의 accept 이벤트
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        // Socket Channel의 read 이벤트
        EventLoopGroup childGroup = new NioEventLoopGroup(4);
        // 총 4개의 쓰레드를 사용하는 EventExecutorGroup 선언
        EventExecutorGroup eventExecutorGroup = new DefaultEventLoopGroup(4);

        try {
            // ServerBootstrap 사용
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            var server = serverBootstrap
                    // 2가지
                    .group(parentGroup, childGroup)
                    // parent가 어떤 채널에 대해서 accept를 받아야할까? 아래 채널.
                    // 내부적으로 인스턴스를 생성하고, register한다.
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast( // LoggingHandler 등록
                                    eventExecutorGroup, new LoggingHandler(LogLevel.INFO)
                            );
                            ch.pipeline().addLast(
                                    new StringEncoder(),
                                    new StringDecoder(),
                                    new NettyEchoServerHandler() // inbond
                            );
                        }
                    });

            server.bind(8080).sync() // 닫히는걸 막음
                    .addListener(new FutureListener<>() {
                        @Override
                        public void operationComplete(Future<Void> future) throws Exception {
                            if (future.isSuccess()) {
                                log.info("Success to bind 8080");
                            } else {
                                log.error("Fail to bind 8080");
                            }
                        }
                    }).channel().closeFuture().sync(); // channel이 닫힐때까지 유지
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
            eventExecutorGroup.shutdownGracefully();
        }
    }
}
