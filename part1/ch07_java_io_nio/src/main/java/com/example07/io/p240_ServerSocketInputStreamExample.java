package com.example07.io;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class p240_ServerSocketInputStreamExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start maiin");
        // 서버 소켓 생성
        ServerSocket serverSocket = new ServerSocket(8080);

        // 클라이언트 접속 대기 (accept가 끝나면 반환값으로 client의 socket을 전달)
        // 클라이언트가 접속할때까지 blocking
        Socket clientSocket = serverSocket.accept();

        // client와 소통할 수 있는 소켓
        var inputStream = clientSocket.getInputStream();
        // buffer 사이즈만큼 미리 읽기
        var bis = new BufferedInputStream(inputStream);
        byte[] buffer = new byte[1024]; // 1024 byte에 해당하는 데이터 읽기
        int bytesRead = bis.read(buffer);
        String inputLine = new String(buffer, 0, bytesRead);
        log.info("bytes: {}", inputLine);

        clientSocket.close(); // 클라이언트 소켓 닫기
        serverSocket.close(); // 서버 소켓 닫기
        log.info("end main");
    }
}
