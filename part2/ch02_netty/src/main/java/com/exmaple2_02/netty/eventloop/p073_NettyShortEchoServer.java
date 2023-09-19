package com.exmaple2_02.netty.eventloop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class p073_NettyShortEchoServer {
    private static ChannelInboundHandler echoHandler() {
        return new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                if (msg instanceof String) {
                    var request = (String) msg;
                    log.info("EchoHandler.channelRead: " + request);

                    ctx.writeAndFlush(request)
                            .addListener(ChannelFutureListener.CLOSE);
                }
            }
        };
    }

    @SneakyThrows
    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup(4);

        var bootstrap = new ServerBootstrap();
        var executorGroup = new DefaultEventExecutorGroup(4);
        var stringEncoder = new StringEncoder();
        var stringDecoder = new StringDecoder();

        var bind = bootstrap
                .group(parentGroup, childGroup) // EventLoopGroup 등록
                .channel(NioServerSocketChannel.class) // instance 자동 생성
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) { // connect 되었을때 실행할 코드
                        ch.pipeline()
                                .addLast(executorGroup, new LoggingHandler(LogLevel.INFO))
                                .addLast(stringEncoder, stringDecoder, echoHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .bind(8080);

        bind.sync().addListener(future -> {
            if (future.isSuccess()) {
                log.info("Server bound to port 8080");
            } else {
                log.info("Failed to bind to port 8080");
                parentGroup.shutdownGracefully();
                childGroup.shutdownGracefully();
            }
        });
    }
}