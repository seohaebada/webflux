package com.example07.niononblocking;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@Slf4j
public class p283_SocketChannelNonBlockingConnectOnlyExample {
    public static void main(String[] args) throws IOException {
        log.info("start main");
        try (var socketChannel = SocketChannel.open()) {
            var address = new InetSocketAddress("localhost", 8080);
            socketChannel.configureBlocking(false); // non-blocking
            var connected = socketChannel.connect(address);
            assert !connected;
        }
        log.info("end main");
    }
}
