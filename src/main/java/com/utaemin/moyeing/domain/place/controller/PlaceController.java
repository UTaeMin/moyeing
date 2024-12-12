package com.utaemin.moyeing.domain.place.controller;


import com.utaemin.moyeing.domain.place.dto.PlaceRequest;
import com.utaemin.moyeing.domain.place.dto.PlaceResponse;
import com.utaemin.moyeing.domain.place.service.PlaceService;

import com.utaemin.moyeing.response.ApiResponse;
import com.utaemin.moyeing.response.ApiResponsePlaceEnum;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<ApiResponse<PlaceResponse.PlaceInfoResponse>> savePlaceInfo(@RequestBody PlaceRequest.PlaceInfoRequest placeInfoRequest) {
        PlaceResponse.PlaceInfoResponse placeInfoResponse=placeService.savePlaceInfo(placeInfoRequest.teamName(), placeInfoRequest.address(), placeInfoRequest.time());
        return ApiResponse.of(ApiResponse.of(ApiResponsePlaceEnum.PLACE_SAVE_SUCCESS,placeInfoResponse));
    }





}
