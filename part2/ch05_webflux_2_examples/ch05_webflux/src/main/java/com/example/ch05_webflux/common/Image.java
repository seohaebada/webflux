package com.example.ch05_webflux.common;

import lombok.Data;

@Data
public class Image {
    private final String id;
    private final String name;
    private final String url;
}
