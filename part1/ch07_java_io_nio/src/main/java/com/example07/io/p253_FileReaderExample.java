package com.example07.io;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class p253_FileReaderExample {
    public static void main(String[] args) throws IOException {
        log.info("start main");
        var file = new File(p253_FileReaderExample.class
                .getClassLoader()
                .getResource("koreanhello.txt").getFile());

        var charset = StandardCharsets.UTF_8;
        try (var fis = new FileReader(file, charset)) { // 3바이트 단위
            var value = 0;

            while ((value = fis.read()) != -1) {
                log.info("value: {}", (char)value);
            }
        }
        log.info("end main");
    }
}
