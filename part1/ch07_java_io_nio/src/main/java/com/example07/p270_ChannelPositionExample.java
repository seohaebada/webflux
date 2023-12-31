package com.example07;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class p270_ChannelPositionExample {
    public static void main(String[] args) throws IOException {
        log.info("start main");
        var file = new File(p270_ChannelPositionExample.class
                .getClassLoader()
                .getResource("hello.txt")
                .getFile());

        try (var fileChannel = FileChannel.open(file.toPath())) {
            var byteBuffer = ByteBuffer.allocateDirect(1024);
            logPosition("allocate", byteBuffer);

            // file로부터 값을 읽어서 byteBuffer에 write
            /* write) position: 11, limit: 1024, capacity: 1024 */
            fileChannel.read(byteBuffer); // (buffer 입장에서는 write)
            logPosition("write", byteBuffer);

            // flip()을 호출하여 읽기모드로 전환
            /* flip1) position: 0, limit: 11, capacity: 1024 */
            byteBuffer.flip();
            logPosition("flip1", byteBuffer);

            // 읽기모드로 전환하여 처음부터 limit(마지막까지 write한 위치까지)까지 읽음
            var result = StandardCharsets.UTF_8.decode(byteBuffer);
            log.info("result: {}", result);
            logPosition("read1", byteBuffer);

            /* rewind) position: 0, limit: 11, capacity: 1024 */
            byteBuffer.rewind();
            logPosition("rewind", byteBuffer);

            var result2 = StandardCharsets.UTF_8.decode(byteBuffer);
            log.info("result2: {}", result2);
            logPosition("read2", byteBuffer);

            /* 결과 없음 */
            var result3 = StandardCharsets.UTF_8.decode(byteBuffer);
            log.info("result3: {}", result3);
            logPosition("read3", byteBuffer);

            /*  clear) position: 0, limit: 1024, capacity: 1024 */
            byteBuffer.clear();
            logPosition("clear", byteBuffer);
        }
        log.info("end main");
    }

    private static void logPosition(String action, ByteBuffer byteBuffer) {
        log.info("{}) position: {}, limit: {}, capacity: {}",
                action,
                byteBuffer.position(),
                byteBuffer.limit(),
                byteBuffer.capacity());
    }
}
