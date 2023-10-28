package com.example.ch07_websocket._실습.websocket.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Chat {
    private String message;
    private String from;
}