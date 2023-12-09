package com.r2dbc.webflux.common;

import lombok.Data;

@Data
public class Image {
    private final String id;
    private final String name;
    private final String url;
}
