package com.example07.io;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class p234_FileInputStreamExample {
    public static void main(String[] args) throws IOException {
        log.info("start main");

        // file로부터 byte 단위로 값을 읽을 수 있다.
        var file = new File(p234_FileInputStreamExample.class
                .getClassLoader()
                .getResource("data.txt").getFile());

        // file을 읽어오는 동안 blocking
        try (var fis = new FileInputStream(file)) {
            var value = 0;

            // 읽을값이 있는동안 계속 시도 (끝에 new line도 포함)
            while ((value = fis.read()) != -1) {
                log.info("value: {}", (char)value);
            }
        }
        log.info("end main");
    }
}
