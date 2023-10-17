package com.example.ch05_webflux.common;

import lombok.Data;

@Data
public class Article {
    private final String id;
    private final String title;
    private final String content;
}
