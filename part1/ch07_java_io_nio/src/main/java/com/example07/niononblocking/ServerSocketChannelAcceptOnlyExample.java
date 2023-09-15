package com.example07.niononblocking;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

@Slf4j
public class ServerSocketChannelAcceptOnlyExample {
    public static void main(String[] args) throws IOException {
        log.info("start main");
        try (var serverChannel = ServerSocketChannel.open()) {
            var address = new InetSocketAddress("localhost", 8080);
            serverChannel.bind(address);
            serverChannel.configureBlocking(false); // non-blocking 모드로 변경

            var clientSocket = serverChannel.accept(); // 바로 다음줄로 넘어감
            assert clientSocket == null;

            while (true) {
                clientSocket = serverChannel.accept();
                if (clientSocket != null) {
                    log.info("clientSocket: {}", clientSocket);
                }
            }
        }
//        log.info("end main");
    }
}
