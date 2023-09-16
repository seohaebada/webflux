package com.example07.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class p277_SocketChannelExample {
    public static void main(String[] args) throws IOException {
        log.info("start main");
        try (var socketChannel = SocketChannel.open()) {
            var address = new InetSocketAddress("localhost", 8080);
            var connected = socketChannel.connect(address);
            log.info("connected: {}", connected); // connect 까지 blocking

            String request = "This is client.";
            ByteBuffer requestBuffer = ByteBuffer.wrap(request.getBytes());
            socketChannel.write(requestBuffer);
            requestBuffer.clear(); // 초기화

            ByteBuffer res = ByteBuffer.allocateDirect(1024);
            while (socketChannel.read(res) > 0) {
                res.flip();
                log.info("response: {}", StandardCharsets.UTF_8.decode(res));
                res.clear();
            }
        }
        log.info("end main");
    }
}
