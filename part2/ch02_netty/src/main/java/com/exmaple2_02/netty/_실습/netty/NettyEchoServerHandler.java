package com.exmaple2_02.netty._실습.netty;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            log.info("Received: {}", msg);
            ctx.writeAndFlush(msg) // 아무것도 하지 않고 msg를 넘긴다.
                    .addListener(ChannelFutureListener.CLOSE); // future의 channel을 close하겠다.
        }
    }
}
