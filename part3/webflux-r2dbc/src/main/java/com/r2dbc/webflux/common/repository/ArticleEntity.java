package com.r2dbc.webflux.common.repository;

import lombok.Data;

@Data
public class ArticleEntity {
    private final String id;
    private final String title;
    private final String content;
    private final String userId;
}
