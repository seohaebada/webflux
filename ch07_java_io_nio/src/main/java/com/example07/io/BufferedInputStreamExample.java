package com.example07.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class BufferedInputStreamExample {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        log.info("start main");
        var file = new File(BufferedInputStreamExample.class
                .getClassLoader()
                .getResource("data.txt").getFile());

        try (var fis = new FileInputStream(file)) {
            // buffer 사이즈만큼 미리 조회
            // 너무 자주 파일에 접근하는걸 막는다.
            try (var bis = new BufferedInputStream(fis)) {
                var value = 0;

                // 미리 저장한 buffer 데이터 반환
                while ((value = bis.read()) != -1) {
                    log.info("value: {}", (char) value);
                }
            }
        }
        log.info("end main");
    }
}
