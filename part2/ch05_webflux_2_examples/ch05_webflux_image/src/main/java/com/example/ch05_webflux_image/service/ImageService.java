package com.example.ch05_webflux_image.service;

import com.example.ch05_webflux_image.entity.common.Image;
import com.example.ch05_webflux_image.repository.ImageReactorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ImageService {
    private ImageReactorRepository imageRepository = new ImageReactorRepository();

    public Mono<Image> getImageById(String imageId) {
        return imageRepository.findById(imageId)
                .map(imageEntity ->
                        new Image(
                                imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl()
                        )
                );
    }
}
