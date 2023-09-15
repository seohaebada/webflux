package com.example07._실습.nioserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class JavaIOServer {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            // 응답을 계속 받는다.
            while (true) {
                // clientSocket 연결 대기
                Socket clientSocket = serverSocket.accept();

                // 연결이 되고나면 아래 수행
                // 클라이언트 요청 받기
                byte[] requestBytes = new byte[1024];
                InputStream in = clientSocket.getInputStream();
                in.read(requestBytes);
                log.info("request: {}", new String(requestBytes).trim());

                // 결과 내리기
                OutputStream out = clientSocket.getOutputStream();
                String response = "This is server";
                out.write(response.getBytes());
                out.flush();
            }
        }
    }
}
