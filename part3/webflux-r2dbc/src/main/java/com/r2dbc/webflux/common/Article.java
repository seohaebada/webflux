package com.r2dbc.webflux.common;

import lombok.Data;

@Data
public class Article {
    private final String id;
    private final String title;
    private final String content;
}
