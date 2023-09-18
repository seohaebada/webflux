package com.exmaple2_02.netty.eventloop;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.StandardCharsets;

public class p056_SampleChannelOutboundHandler
        extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(
            // 이전 handler로부터 받은 값 msg
            ChannelHandlerContext ctx, Object msg, ChannelPromise promise
    ) {
        if (msg instanceof String) {
            ctx.write(msg, promise);
        } else if (msg instanceof ByteBuf) {
            var buf = (ByteBuf) msg;
            var len = buf.readableBytes();
            var charset = StandardCharsets.UTF_8;
            var body = buf.readCharSequence(len, charset);
            ctx.write(body, promise);
        }
    }
}
