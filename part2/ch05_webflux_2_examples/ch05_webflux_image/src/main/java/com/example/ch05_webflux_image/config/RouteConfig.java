package com.example.ch05_webflux_image.config;

import com.example.ch05_webflux_image.handler.ImageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfig {
    @Bean
    RouterFunction router(
            ImageHandler imageHandler
    ) {
        // GET http://localhost:8081/api/images/100000
        return route()
                .path("/api", b1 -> b1
                        .path("/images", b2 -> b2
                                .GET("/{imageId:[0-9]+}", imageHandler::getImageById)
                        )
                ).build();
    }
}
