package com.example.ch05_webflux_image.entity.common;

import lombok.Data;

@Data
public class Image {
    private final String id;
    private final String name;
    private final String url;
}
