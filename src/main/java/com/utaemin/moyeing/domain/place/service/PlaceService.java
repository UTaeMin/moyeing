package com.utaemin.moyeing.domain.place.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final RedisTemplate<String, Object> redisTemplate;
}
