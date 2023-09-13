package com.example07.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public class ByteBufferExample {
    public static void main(String[] args) {
        // DirectByteBuffer
        var directByteBuffer = ByteBuffer.allocateDirect(1024);
        assert directByteBuffer.isDirect();

        // HeapByteBuffer
        var heapByteBuffer = ByteBuffer.allocate(1024);
        assert !heapByteBuffer.isDirect();

        var byteBufferByWrap = ByteBuffer.wrap("hello".getBytes());
        assert !byteBufferByWrap.isDirect();
    }
}
