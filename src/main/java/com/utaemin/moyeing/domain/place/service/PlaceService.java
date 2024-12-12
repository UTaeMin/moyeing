package com.utaemin.moyeing.domain.place.service;

import com.utaemin.moyeing.domain.place.dto.PlaceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {

    private final RedisTemplate<String, Object> redisTemplate;


    public PlaceResponse.PlaceInfoResponse savePlaceInfo(String teamName, String address, String time) {
        log.info(":::장소 정보 저장 시작:::");
        String placeKey = "Place:" + teamName;
        String timeKey = "Time:"+teamName;

        redisTemplate.opsForValue().set(placeKey, address);
        redisTemplate.opsForValue().set(timeKey, time);

        redisTemplate.expire(placeKey, Duration.ofMinutes(30));
        redisTemplate.expire(timeKey, Duration.ofMinutes(30));

        log.info(":::장소 정보 저장 종료:::");

        return new PlaceResponse.PlaceInfoResponse(teamName);
    }
}
