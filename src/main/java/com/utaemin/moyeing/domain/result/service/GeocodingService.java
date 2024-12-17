package com.utaemin.moyeing.domain.result.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class GeocodingService {

    @Value("${naver.maps.client-id}")
    private String clientId;

    @Value("${naver.maps.client-secret}")
    private String clientSecret;

    @Value("${naver.maps.geocoding-url}")
    private String geocodingUrl;

    private final OkHttpClient client = new OkHttpClient();

    public String getGeocode(String address) throws Exception {
        // URL 생성
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String url = geocodingUrl + "/geocode?query=" + encodedAddress;

        // HTTP 요청 생성
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-ncp-apigw-api-key-id", clientId)
                .addHeader("x-ncp-apigw-api-key", clientSecret)
                .addHeader("Accept", "application/json")
                .build();

        log.info(request.toString());

        // 요청 실행 및 응답 처리
        try (Response response = client.newCall(request).execute()) {

            log.info("Full URL: {}", url);
            // 디버깅을 위한 상세 로그
            log.info("Status Code: {}", response.code());
            String responseBody = response.body().string();
            log.info("Response Body: {}", responseBody);

            if (!response.isSuccessful()) {
                throw new RuntimeException("Geocoding API 호출 실패: " + response.message());
            }

            // JSON 응답 파싱
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);

            // 좌표 추출
            JsonNode addresses = rootNode.path("addresses");
            if (addresses.isArray() && addresses.size() > 0) {
                JsonNode firstAddress = addresses.get(0);
                String latitude = firstAddress.path("y").asText(); // 위도
                String longitude = firstAddress.path("x").asText(); // 경도
                return "Latitude: " + latitude + ", Longitude: " + longitude;
            } else {
                throw new RuntimeException("좌표 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            log.error("Geocoding API 호출 중 오류 발생", e);
            throw e;
        }
    }
}
