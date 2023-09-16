package com.example07.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class p274_FileChannelReadExample {
    public static void main(String[] args) throws IOException {
        log.info("start main");
        var file = new File(p274_FileChannelReadExample.class
                .getClassLoader()
                .getResource("hello.txt")
                .getFile());

        try (var fileChannel = FileChannel.open(file.toPath())) {
            var byteBuffer = ByteBuffer.allocateDirect(1024);
            fileChannel.read(byteBuffer); // buffer에 write
            byteBuffer.flip(); // 처음부터 읽어들인다.

            // 결과 출력
            var result = StandardCharsets.UTF_8.decode(byteBuffer);
            log.info("result: {}", result);
        }
        log.info("end main");
    }
}
