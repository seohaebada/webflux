package com.example07._실습.nioserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JavaNIOBlockingServer {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");

        // Channel
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            while (true) {
                SocketChannel clientSocket = serverSocket.accept(); // blocking

                ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
                clientSocket.read(requestByteBuffer);
                requestByteBuffer.flip(); // 위에서 읽어들인 부분까지 읽기위함

                // 결과 decode
                String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
                log.info("request: {}", requestBody);

                ByteBuffer responeByteBuffer = ByteBuffer.wrap("This is server".getBytes());
                clientSocket.write(responeByteBuffer);
                clientSocket.close();
            }
        }
    }
}
