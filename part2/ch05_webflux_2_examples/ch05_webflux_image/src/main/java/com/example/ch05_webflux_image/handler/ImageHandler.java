package com.example.ch05_webflux_image.handler;

import com.example.ch05_webflux_image.handler.dto.ImageResponse;
import com.example.ch05_webflux_image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ImageHandler {
    private final ImageService imageService;

    public Mono<ServerResponse> getImageById(ServerRequest serverRequest) {
        String imageId = serverRequest.pathVariable("imageId");

        return imageService.getImageById(imageId)
                .map(image ->
                    new ImageResponse(image.getId(), image.getName(), image.getUrl())
                ).flatMap(imageResp -> // Mono<Mono<ServerResponse>> -> Mono<ServerResponse>
                        ServerResponse.ok().bodyValue(imageResp)
                ).onErrorMap(e -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
