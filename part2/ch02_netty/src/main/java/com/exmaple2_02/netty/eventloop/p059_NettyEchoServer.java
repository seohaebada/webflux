package com.exmaple2_02.netty.eventloop;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;


// p.61
@Slf4j
public class p059_NettyEchoServer {
    private static ChannelInboundHandler echoHandler() {
        // TailHandler -> LoggingHandler -> echoHandler
        return new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                if (msg instanceof ByteBuf) {
                    try {
                        var buf = (ByteBuf) msg;
                        var len = buf.readableBytes();
                        var charset = StandardCharsets.UTF_8;
                        var body = buf.readCharSequence(len, charset);
                        log.info("EchoHandler.channelRead: " + body);

                        buf.readerIndex(0); // rewind
                        var result = buf.copy();
                        ctx.writeAndFlush(result) // flush 후에 채널을 닫아라
                                .addListener(ChannelFutureListener.CLOSE);
                    } finally {
                        // 다 읽었으므로 해제
                        ReferenceCountUtil.release(msg);
                    }
                }
            }
        };
    }

    private static ChannelInboundHandler acceptor(EventLoopGroup childGroup) {
        var executorGroup = new DefaultEventExecutorGroup(4);

        return new ChannelInboundHandlerAdapter() {
            // msg 는 accept의 결과물인 SocketChannel
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                log.info("Acceptor.channelRead");
                if (msg instanceof SocketChannel) {
                    // SocketChannel 로 받고,
                    SocketChannel socketChannel = (SocketChannel) msg;
                    // 별도의 쓰레드에서 LoggingHandler를 돌리겠다는 설정 (별도의 executorGroup)
                    // = 별개의 eventExecutor를 갖게해서, eventLoop에서 돌리지않고 별도의 쓰레드로 돌리겠다.
                    socketChannel.pipeline().addLast(
                            executorGroup, new LoggingHandler(LogLevel.INFO));
                    socketChannel.pipeline().addLast(
                            echoHandler()
                    );
                    // read 이벤트 담당하는 childGroup에 등록
                    childGroup.register(socketChannel);
                }
            }
        };
    }

    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup(4);

        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();
        parentGroup.register(serverSocketChannel);
        serverSocketChannel.pipeline().addLast(acceptor(childGroup));

        serverSocketChannel.bind(new InetSocketAddress(8080))
                .addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("Server bound to port 8080");
                    }
                });
    }
}