package com.redis.image.service;

import com.redis.image.common.Image;
import com.redis.image.common.repository.ImageEntity;
import com.redis.image.repository.ImageReactorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageReactorRepository imageRepository;

    public Mono<Image> getImageById(String imageId) {
        return imageRepository.findById(imageId)
                .map(this::map);
    }

    public Mono<Image> createImage(String id, String name, String url) {
        return imageRepository.create(id, name, url)
                .map(this::map);
    }

    private Image map(ImageEntity imageEntity) {
        return new Image(
                imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl()
        );
    }
}
