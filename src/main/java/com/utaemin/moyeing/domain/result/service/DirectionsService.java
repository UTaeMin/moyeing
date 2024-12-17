package com.utaemin.moyeing.domain.result.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DirectionsService {

    @Value("${naver.maps.client-id}")
    private String clientId;

    @Value("${naver.maps.client-secret}")
    private String clientSecret;

    @Value("${naver.maps.directions-url}")
    private String directionsUrl;

    private final OkHttpClient client = new OkHttpClient();

    public JsonNode getDrivingDirections(String start, String goal) throws Exception {
        // URL 생성
        String url = directionsUrl + "?start=" + start + "&goal=" + goal;

        // HTTP 요청 생성
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-NCP-APIGW-API-KEY-ID", clientId)
                .addHeader("X-NCP-APIGW-API-KEY", clientSecret)
                .build();

        // 요청 실행 및 응답 처리
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("API 호출 실패: " + response.message());
            }

            // JSON 응답 파싱
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.body().string());
        }
    }
}
