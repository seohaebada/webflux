package com.exmaple2_02.netty._실습.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * client가 server와의 연결이 준비가 됬을때
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 보낼 메시지
        // close 하지 않음 -> server 응답을 기다리기 전에 닫아버리므로
        ctx.writeAndFlush("This is client.");
    }

    /**
     * server로 받은 결과를 읽는다.
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            log.info("Received: {}", msg);
        }
    }
}
