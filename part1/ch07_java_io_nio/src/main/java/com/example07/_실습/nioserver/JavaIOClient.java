package com.example07._실습.nioserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class JavaIOClient {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        try (Socket socket = new Socket()) {
            // 서버에 연결
            socket.connect(new InetSocketAddress("localhost", 8080));

            // 클라이언트가 보내는것
            OutputStream out = socket.getOutputStream();
            String requestBody = "This is client";
            out.write(requestBody.getBytes());
            out.flush();

            // 결과를 받자
            InputStream in = socket.getInputStream();
            byte[] responseBytes = new byte[1024];
            in.read(responseBytes);
            log.info("result: {}", new String(responseBytes).trim());
        }
        log.info("end main");
    }
}
