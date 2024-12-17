package com.utaemin.moyeing.domain.result.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utaemin.moyeing.domain.member.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ResultService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final GeocodingService geocodingService;
    private final DirectionsService directionsService;


    public void getResult(String teamName) throws Exception {

        String placeKey = "Place:" + teamName;
        String timeKey = "Time:" + teamName;
        String teamKey = "Team:" + teamName;

        // 1. Redis에서 팀원의 위치 리스트 가져와서 이름:주소 형태로 추출
        Map<String,String> memberInfo = getMemberInfo(teamKey);

        // 2. 각 위치를 Geocoding하여 좌표 리스트에 추가
        Map<String, String> geoInfo = new HashMap<>();
        for (String name : memberInfo.keySet()) {
            geoInfo.put(name, geocodingService.getGeocode(memberInfo.get(name)));
        }

        log.info(geoInfo.values().toString());


        // 3. 목적지 좌표 가져오기
        String placeAddress = (String) redisTemplate.opsForValue().get(placeKey);
        String placeGeocodeResponse = geocodingService.getGeocode(placeAddress);

        // 4. 각 팀원의 위치에서 목적지까지 경로 정보 가져오기
        List<JsonNode> routes = new ArrayList<>();
//        for (String start : startCoords) {
//            try {
//                JsonNode directionsResponse = directionsService.getDrivingDirections(start, goalCoords);
//                routes.add(directionsResponse);
//                log.info("경로 계산 성공: 시작({}) -> 목적지({})", start, goalCoords);
//            } catch (Exception e) {
//                log.error("경로 계산 실패: 시작({}) -> 목적지({})", start, goalCoords, e);
//            }
//        }

    }

    private Map<String, String> getMemberInfo(String teamKey) {
        // 결과를 저장할 HashMap
        Map<String, String> memberMap = new HashMap<>();

        // Redis에서 리스트 데이터를 가져옴
        List<Object> jsonList;
        jsonList = redisTemplate.opsForList().range(teamKey, 0, -1);

        // 데이터가 null 또는 비어있으면 빈 Map 반환
        if (jsonList == null || jsonList.isEmpty()) {
            log.warn("No data found for key: " + teamKey);
            return memberMap;
        }

        // 각 객체를 MemberInfoRequest로 캐스팅하여 Map에 저장
        for (Object obj : jsonList) {
            if (obj instanceof MemberRequest.MemberInfoRequest) {
                MemberRequest.MemberInfoRequest member = (MemberRequest.MemberInfoRequest) obj;
                memberMap.put(member.name(), member.address());
            } else {
                log.warn("Unexpected data type: " + obj.getClass());
            }
        }
        return memberMap;
    }



}
