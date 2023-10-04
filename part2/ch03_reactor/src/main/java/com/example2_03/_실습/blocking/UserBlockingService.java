package com.example2_03._실습.blocking;

import com.example2_03._실습.blocking.repository.ArticleRepository;
import com.example2_03._실습.blocking.repository.FollowRepository;
import com.example2_03._실습.blocking.repository.ImageRepository;
import com.example2_03._실습.blocking.repository.UserRepository;
import com.example2_03._실습.common.Article;
import com.example2_03._실습.common.Image;
import com.example2_03._실습.common.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserBlockingService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    private final FollowRepository followRepository;

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id)
                .map(user -> {
                    var image = imageRepository.findById(user.getProfileImageId())
                            .map(imageEntity -> {
                                return new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl());
                            });

                    var articles = articleRepository.findAllByUserId(user.getId())
                            .stream().map(articleEntity ->
                                    new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent()))
                            .collect(Collectors.toList());

                    var followCount = followRepository.countByUserId(user.getId());

                    return new User(
                            user.getId(),
                            user.getName(),
                            user.getAge(),
                            image,
                            articles,
                            followCount
                    );
                });
    }
}
