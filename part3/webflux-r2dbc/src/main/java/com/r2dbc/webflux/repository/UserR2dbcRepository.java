package com.r2dbc.webflux.repository;

import com.r2dbc.webflux.common.repository.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserR2dbcRepository
        extends R2dbcRepository<UserEntity, Long> {
}
