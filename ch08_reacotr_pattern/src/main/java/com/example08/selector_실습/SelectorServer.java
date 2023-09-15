package com.example08.selector_실습;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SelectorServer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open();
             Selector selector = Selector.open();
        ) {
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            serverSocket.configureBlocking(false);
            // accept() 이벤트가 준비되면 쓰레드에 알려줌
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select(); // 준비된 이벤트가 없다면 blocking
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();

                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove(); // 이벤트 중복 처리 할 수 있으므로 제거

                    // key 안에는 어떤 채널, 어떤 Selector 등 어떤 준비된 이벤트인지의 정보가 담겨있음
                    if (key.isAcceptable()) { // accept 가능 상태라면,
                        // null이 될 수 없다
                        SocketChannel clientSocket = ((ServerSocketChannel)key.channel()).accept();
                        clientSocket.configureBlocking(false);
                        clientSocket.register(selector, SelectionKey.OP_READ); // read 이벤트 등록
                    } else if (key.isReadable()) {
                        SocketChannel clientSocket = (SocketChannel) key.channel();

                        String requestBody = handleRequest(clientSocket);
                        sendResponse(clientSocket, requestBody);
                    }
                }
            }
        }
    }

    @SneakyThrows
    private static String handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        clientSocket.read(requestByteBuffer);

        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("request: {}", requestBody);

        return requestBody;
    }

    @SneakyThrows
    private static void sendResponse(SocketChannel clientSocket, String requestBody) {
        Thread.sleep(10);

        String content = "received: " + requestBody;
        ByteBuffer responeByteBuffer = ByteBuffer.wrap(content.getBytes());
        clientSocket.write(responeByteBuffer);
        clientSocket.close();
    }
}
