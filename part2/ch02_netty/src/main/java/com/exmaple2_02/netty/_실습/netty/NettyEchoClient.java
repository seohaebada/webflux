package com.exmaple2_02.netty._실습.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;

public class NettyEchoClient {
    @SneakyThrows
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        try {
            Bootstrap bootstrap = new Bootstrap();

            var client = bootstrap
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    // connect 된 후에 수행할 handler 적용
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception { // 여기서의 Channel은 SocketChannl 이고, 이게 connect 됬을때
                            ch.pipeline().addLast(
                                    new LoggingHandler(),
                                    new StringEncoder(), // byteBuf -> String
                                    new StringDecoder(), // String -> byteBuf
                                    new NettyEchoClientHandler()
                            );
                        }
                    });

            // connect()를 통해서 연결
            client.connect("localhost", 8080).sync()
                    .channel().closeFuture().sync(); // close()되기 전까지 blocking
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
