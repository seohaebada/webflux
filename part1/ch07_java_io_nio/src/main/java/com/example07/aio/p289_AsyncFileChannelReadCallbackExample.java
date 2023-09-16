package com.example07.aio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

@Slf4j
public class p289_AsyncFileChannelReadCallbackExample {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start main");
        var file = new File(p289_AsyncFileChannelReadCallbackExample.class
                .getClassLoader()
                .getResource("hello.txt")
                .getFile());

        var channel = AsynchronousFileChannel.open(file.toPath());
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        // buffer 을 처음부터 읽고
        // 나중에 callback 실행시 두번째 인자로 오는 값 (attachment)
        // 콜백함수 completed, failed
        channel.read(buffer, 0, null, new CompletionHandler<>() {
            @SneakyThrows
            @Override
            public void completed(Integer result, Object attachment) {
                buffer.flip();
                var resultString = StandardCharsets.UTF_8.decode(buffer);
                log.info("result: {}", resultString);
                channel.close();
            }

            @Override
            public void failed(Throwable ex, Object attachment) { /* do nothing */ }
        });

        while (channel.isOpen()) { // 닫힐때까지 (모두 읽을때까지)
            log.info("Reading...");
        }
        log.info("end main");
    }
}
