package com.exmaple2_02.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class EventLoopMain {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
    }
}
